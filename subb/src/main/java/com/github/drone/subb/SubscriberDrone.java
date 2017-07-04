package com.github.drone.subb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.ros.exception.ServiceException;
import org.ros.message.MessageListener;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;
import org.ros.node.service.*;
import org.yaml.snakeyaml.Yaml;

import SimpleTester.TesterThread;
import geometry_msgs.Point;
import geometry_msgs.Pose;
import rosjava_test_msgs.AddTwoIntsRequest;
import rosjava_test_msgs.AddTwoIntsResponse;
import std_srvs.EmptyRequest;
import std_srvs.EmptyResponse;

public class SubscriberDrone extends AbstractNodeMain {

	private static IDrone drone = new Drone("quadrotor");
	private static Gazebo gazebo = null;
	private static Application app = null;
	private Configuration config = null;
	private static ConnectedNode conNode = null;

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("drone/listener");
	}

	@Override
	public void onStart(ConnectedNode connectedNode) {
		// extra quadrotor
		//		try {
		//			Runtime.getRuntime().exec("roslaunch beginner_tutorials drone.launch namespace:=quadrotor",null,new File("/home/ben/catkin_ws"));
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		drone.setName("quadrotor");
		conNode = connectedNode;
		try {
			setupConfig();
			setupApplicationCommunication(connectedNode);
			setupSubscribers(connectedNode);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private void setupSubscribers(ConnectedNode connectedNode) {
		java.lang.String name;
		name = this.config.getLocation();
		if(name != null){
			Subscriber<geometry_msgs.PoseStamped> subscriberLocation = connectedNode.newSubscriber(drone.getName()+name, geometry_msgs.PoseStamped._TYPE);
			subscriberLocation.addMessageListener(new MessageListener<geometry_msgs.PoseStamped>() {

				@Override
				public void onNewMessage(geometry_msgs.PoseStamped message) {
					Point pos = message.getPose().getPosition();
					getDrone().setPosition(pos);
				}
			});
		}

		name = this.config.getSimulator();
		if(name != null){
			//TODO add objects from environnement
			final Subscriber<gazebo_msgs.ModelStates> subscriberGazebo = connectedNode.newSubscriber(name, gazebo_msgs.ModelStates._TYPE);
			subscriberGazebo.addMessageListener(new MessageListener<gazebo_msgs.ModelStates>() {
				@Override
				public void onNewMessage(gazebo_msgs.ModelStates message) {
					//System.out.println(message.getName());
					if(message.getName().contains("quadrotor")){
						//subscriberGazebo.shutdown();
						List<Pose> poseLst = message.getPose();
						drone.getEnvironnement().addObjectsToEnvironnemnt(poseLst,message.getName());
						gazebo.setRunning(true);
					};
				}
			});
		}
		name = this.config.getGPS();
		if(name != null){
			Subscriber<sensor_msgs.NavSatFix> subscriberGPS = connectedNode.newSubscriber(drone.getName()+name, sensor_msgs.NavSatFix._TYPE);
			subscriberGPS.addMessageListener(new MessageListener<sensor_msgs.NavSatFix>() {
				@Override
				public void onNewMessage(sensor_msgs.NavSatFix message) {
					double alt = message.getAltitude();
					double l = message.getLatitude();
					double lat = message.getLongitude();
					getDrone().GPSReading(alt, l, lat);
				}
			});
		}
		Subscriber<rosgraph_msgs.Clock> subscriberTime = connectedNode.newSubscriber("/clock", rosgraph_msgs.Clock._TYPE);
		subscriberTime.addMessageListener(new MessageListener<rosgraph_msgs.Clock>() {

			@Override
			public void onNewMessage(rosgraph_msgs.Clock message) {
				Time time = message.getClock();
				SubscriberDrone.app.setSimulationTime(time);
			}
		});
		Subscriber< geometry_msgs.Vector3> subscriberWind = connectedNode.newSubscriber("/wind", geometry_msgs.Vector3._TYPE);
		subscriberWind.addMessageListener(new MessageListener< geometry_msgs.Vector3>() {

			@Override
			public void onNewMessage( geometry_msgs.Vector3 message) {
				System.out.println("wind: " + message.getX());
				getDrone().getEnvironnement().setWindSpeed(message);
			}
		});
		name = this.config.getVelocity();
		if(name != null){
			Subscriber<geometry_msgs.Vector3Stamped> subscriberVelocity = connectedNode.newSubscriber(drone.getName()+ name, geometry_msgs.Vector3Stamped._TYPE);
			subscriberVelocity.addMessageListener(new MessageListener<geometry_msgs.Vector3Stamped>() {
				@Override
				public void onNewMessage(geometry_msgs.Vector3Stamped message) {
					getDrone().setVelocity(message.getVector());
				}
			});
		}
		name = this.config.getSonar();
		if(name != null){
			Subscriber<sensor_msgs.Range> subscriberSonar = connectedNode.newSubscriber(drone.getName()+ name, sensor_msgs.Range._TYPE);
			subscriberSonar.addMessageListener(new MessageListener<sensor_msgs.Range>() {
				@Override
				public void onNewMessage(sensor_msgs.Range message) {
					getDrone().SonarReading(message.getRange());
				}
			});
		}
		name = this.config.getBarometer();
		if(name != null){
			Subscriber<geometry_msgs.PointStamped> subscriberBarometer = connectedNode.newSubscriber(drone.getName()+ name, geometry_msgs.PointStamped._TYPE);
			subscriberBarometer.addMessageListener(new MessageListener<geometry_msgs.PointStamped>() {
				@Override
				public void onNewMessage(geometry_msgs.PointStamped message) {
					//System.out.println(message.getPoint().getZ());
					getDrone().BaroReading(message.getPoint());
				}
			});
		}
		//TODO battery message are not comming true
		Subscriber<sensor_msgs.BatteryState> subscriberBattery = connectedNode.newSubscriber(drone.getName()+"/battery", sensor_msgs.BatteryState._TYPE);
		subscriberBattery.addMessageListener(new MessageListener<sensor_msgs.BatteryState>() {
			@Override
			public void onNewMessage(sensor_msgs.BatteryState message) {
				System.out.println("******Battery" + message.getPercentage());
				getDrone().BatteryPerc(message.getPercentage());
			}
		});
	}

	private void setupApplicationCommunication(ConnectedNode connectedNode) {
		java.lang.String name = this.config.getApplicationStart();
		connectedNode.newServiceServer(
			    name, rosjava_test_msgs.AddTwoInts._TYPE,
			    new ServiceResponseBuilder<rosjava_test_msgs.AddTwoIntsRequest,rosjava_test_msgs.AddTwoIntsResponse>() {

				@Override
				public void build(AddTwoIntsRequest arg0, AddTwoIntsResponse arg1) throws ServiceException {
					app.setRunning(true);
					while(gazebo == null || !gazebo.isRunning()){Thread.yield();}
					ConnectedNode con = SubscriberDrone.getConNode();
					int startTime = con.getCurrentTime().secs + 2;
					arg1.setSum(startTime);
					startTester(con, startTime);
					
				}
			    }
			);
		name = this.config.getApplicationStop();
		connectedNode.newServiceServer(
			    name, "std_srvs/Empty",
			    new ServiceResponseBuilder<std_srvs.EmptyRequest,std_srvs.EmptyResponse>() {

				@Override
				public void build(EmptyRequest arg0, EmptyResponse arg1) throws ServiceException {
					app.setRunning(false);
				}
			    }
			);
	}

	private void initialize() throws IOException {
		System.out.println("init");
		if(app == null){
			app = new Application();
			app.setPath(this.config.getApplicationbashpath());
			Thread t = new Thread(app);
			t.start();
		}
		if(gazebo == null){
			gazebo = new Gazebo();
			gazebo.setPath(this.config.getSimulatorbashpath());
			gazebo.initialize();
		}
	}

	private void setupConfig() throws IOException {
		File dir = new File(System.getProperty("user.dir"));
        dir =  dir.getParentFile().getParentFile().getParentFile().getParentFile();
        Yaml yaml = new Yaml();
		try(InputStream in = Files.newInputStream(Paths.get(dir+ "/ConfigurationFile.yml"))){
			this.config = yaml.loadAs( in,  Configuration.class);
		}
	}

	private void startTester(ConnectedNode con, int startTime){
		TesterThread test = new TesterThread(SubscriberDrone.getDrone(), app, this, con, startTime);
		Thread t = new Thread(test);
		t.start();
	}

	public static IDrone getDrone() {
		return drone;
	}
	
	public static ConnectedNode getConNode() {
		return conNode;
	}

	public void shutdown(){
		long beginTime = System.currentTimeMillis();
		while(app.isRunning() && (System.currentTimeMillis() - beginTime) < 5000){Thread.yield();}
		Runtime.getRuntime().exit(0);
	}
	
}
