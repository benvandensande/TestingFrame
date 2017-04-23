package com.github.drone.subb;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.ros.message.MessageListener;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;
import org.yaml.snakeyaml.Yaml;

import SimpleTester.TesterThread;
import geometry_msgs.Point;
import geometry_msgs.Pose;
import std_msgs.String;

public class SubscriberDrone extends AbstractNodeMain {

	private static IDrone drone = new Drone();
	private static Gazebo gazebo = null;
	private static Application app = null;
	private Configuration config = null;
	private boolean alreadyStarted = false;

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
		try {
			setupConfig();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		java.lang.String name = this.config.getApplication();
		if(name != null){
			final Publisher<std_msgs.String> applicationPub = 
					connectedNode.newPublisher(name, std_msgs.String._TYPE);
			final Subscriber<std_msgs.String> sub = 
					connectedNode.newSubscriber(name, std_msgs.String._TYPE);
			sub.addMessageListener(new MessageListener<std_msgs.String>() {
				@Override
				public void onNewMessage(std_msgs.String message) {
					if(message.getData().equals("started") && !alreadyStarted){
						alreadyStarted = true;
						System.out.println("message received: " + message.getData());
						while(gazebo == null || !gazebo.isRunning()){Thread.yield();}
						startTester();
						publish(applicationPub, "run");
						System.out.println("message send: run");
						app.setRunning(true);
					}
					else if(message.getData().equals("stopped")){
						System.out.println("message received: " + message.getData());
						app.setRunning(false);
					}
				}
			});
		}

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
					if(message.getName().contains("quadrotor")){
						subscriberGazebo.shutdown();
						List<Pose> poseLst = message.getPose();
						if(poseLst.size() > 2){
							List<Pose> newLst = poseLst.subList(2, poseLst.size()-1);
							for (Pose pose:newLst){
								System.out.println("object at: " + pose);
								drone.getEnvironnement().addObjectToEnvironnemnt(new Object(null, pose.getPosition()));
							}
						}
						gazebo.setRunning(true);
					};
				}
			});
		}
		Subscriber<sensor_msgs.NavSatFix> subscriberGPS = connectedNode.newSubscriber(drone.getName()+"/fix", sensor_msgs.NavSatFix._TYPE);
		subscriberGPS.addMessageListener(new MessageListener<sensor_msgs.NavSatFix>() {

			@Override
			public void onNewMessage(sensor_msgs.NavSatFix message) {
				double status = message.getAltitude();
				getDrone().GPSSignal(!Double.isNaN(status));
			}
		});
		Subscriber<rosgraph_msgs.Clock> subscriberTime = connectedNode.newSubscriber("/clock", rosgraph_msgs.Clock._TYPE);
		subscriberTime.addMessageListener(new MessageListener<rosgraph_msgs.Clock>() {

			@Override
			public void onNewMessage(rosgraph_msgs.Clock message) {
				Time time = message.getClock();
				SubscriberDrone.app.setSimulationTime(time);
			}
		});
		Subscriber< geometry_msgs.Vector3> subscriberWind = connectedNode.newSubscriber("wind", geometry_msgs.Vector3._TYPE);
		subscriberWind.addMessageListener(new MessageListener< geometry_msgs.Vector3>() {

			@Override
			public void onNewMessage( geometry_msgs.Vector3 message) {
				System.out.println(message.getX());
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
		//TODO battery message are not comming true
		Subscriber<sensor_msgs.BatteryState> subscriberBattery = connectedNode.newSubscriber(drone.getName()+"/sonar", sensor_msgs.BatteryState._TYPE);
		subscriberBattery.addMessageListener(new MessageListener<sensor_msgs.BatteryState>() {
			@Override
			public void onNewMessage(sensor_msgs.BatteryState message) {
				System.out.println(message.getPercentage());
			}
		});

		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private void initialize() throws IOException {
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
		Yaml yaml = new Yaml();
		try(InputStream in = Files.newInputStream(Paths.get("/home/ben/rosjava/src/drone/subb/src/main/java/ConfigurationFile.yml"))){
			this.config = yaml.loadAs( in,  Configuration.class);
		}
	}

	private void startTester(){
		TesterThread test = new TesterThread(SubscriberDrone.getDrone(), app);
		Thread t = new Thread(test);
		t.start();
	}

	public static IDrone getDrone() {
		return drone;
	}

	public void shutdown(){
		gazebo.shutdown();
	}

	private void publish(Publisher<String> applicationPub, java.lang.String string) {
		std_msgs.String str = applicationPub.newMessage();
		str.setData(string);
		applicationPub.publish(str);
	}
}
