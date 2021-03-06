package com.github.drone.subb;

import org.ros.exception.RemoteException;
import org.ros.exception.ServiceNotFoundException;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.service.ServiceClient;
import org.ros.node.service.ServiceResponseListener;
import org.ros.node.topic.Publisher;

import geometry_msgs.Point;

import std_srvs.EmptyRequest;
import std_srvs.EmptyResponse;

/**
 * A simple {@link Publisher} {@link NodeMain}.
 */
public class PublisherDrone4 extends AbstractNodeMain {

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("drone/talker");
	}

	@Override
	public void onStart(final ConnectedNode connectedNode) {
		
		//TODO make apart class for starting app
		ServiceClient<rosjava_test_msgs.AddTwoIntsRequest, rosjava_test_msgs.AddTwoIntsResponse> client;
		try {
			client = connectedNode.newServiceClient("start", rosjava_test_msgs.AddTwoInts._TYPE);
			rosjava_test_msgs.AddTwoIntsRequest request = client.newMessage();
			System.out.println(client);

	        client.call(request, new ServiceResponseListener<rosjava_test_msgs.AddTwoIntsResponse>() {
				@Override
				public void onFailure(RemoteException arg0) {
					System.out.println("fail");
					
				}

				@Override
				public void onSuccess(rosjava_test_msgs.AddTwoIntsResponse arg0) {
					while(connectedNode.getCurrentTime().secs < arg0.getSum()){}
					System.out.println("***app started");
					final Publisher< geometry_msgs.PoseStamped> publisher =
							connectedNode.newPublisher("quadrotor/command/pose",  geometry_msgs.PoseStamped._TYPE);
					final Publisher< sensor_msgs.BatteryState> publisherBat =
							connectedNode.newPublisher("quadrotor/battery",  sensor_msgs.BatteryState._TYPE);
					int i = 0;
					while(i<1000){
						publish(publisher, 2 , 0, 0);
						System.out.println("published");
						publish(publisher, 2, 0, 0);
						i += 1;
					}
					try {
						Thread.sleep(7000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i = 0;
					int y = 99;
					publishBattery(publisherBat, y);
					while(i<50){
						y -= 1;
						if (y < 50) publish(publisher,0.5,0,0);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						publishBattery(publisherBat, y);
						//System.out.println("stopped");
						publishBattery(publisherBat, y);
						i += 1;
					}
					System.out.println("done");
//					final Publisher< geometry_msgs.Twist> publisher =
//					connectedNode.newPublisher("quadrotor/cmd_vel",  geometry_msgs.Twist._TYPE);
//					int i = 0;
//					while(i<1000){
//						publish(publisher, 0.3);
//						//System.out.println("published");
//						publish(publisher, 0.3);
//						i += 1;
//					}
//					
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					i = 0;
//					while(i<200){
//						publish(publisher, 0);
//						//System.out.println("stopped");
//						publish(publisher, 0);
//						i += 1;
//					}
//					System.out.println("done");
					
					ServiceClient<EmptyRequest, EmptyResponse> stopClient;
					try {
						stopClient = connectedNode.newServiceClient("stop", "std_srvs/Empty");
						std_srvs.EmptyRequest request = stopClient.newMessage();
						System.out.println(stopClient);
						while(connectedNode.getCurrentTime().secs < 30){}
						stopClient.call(request, new ServiceResponseListener<std_srvs.EmptyResponse>() {
							@Override
							public void onFailure(RemoteException arg0) {
								System.out.println("fail");
								
							}

							@Override
							public void onSuccess(EmptyResponse arg0) {
								System.out.println("app was stopped");
							}
				        });
						Runtime.getRuntime().exit(0);
					
					} catch (ServiceNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						Runtime.getRuntime().exit(0);
					}
				}
	        });
		
		} catch (ServiceNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Runtime.getRuntime().exit(0);
		}
		
	}

	private void publish(final Publisher<geometry_msgs.PoseStamped> publisher, double z, double x, double y) {
		geometry_msgs.PoseStamped str = publisher.newMessage();
		geometry_msgs.Pose pose = str.getPose();
		Point p = pose.getPosition();
		p.setX(x);
		p.setY(y);
		p.setZ(z);
		publisher.publish(str);
//		geometry_msgs.Twist str = publisher.newMessage();
//		geometry_msgs.Vector3 linear =  str.getLinear();
//		linear.setX(0);
//		linear.setY(0);
//		linear.setZ(d);
//		geometry_msgs.Vector3 angular =  str.getAngular();
//		angular.setX(0);
//		angular.setY(0);
//		angular.setZ(0);
//		publisher.publish(str);
	}
	
	private void publishBattery(final Publisher<sensor_msgs.BatteryState> publisher, double z) {
		sensor_msgs.BatteryState str = publisher.newMessage();
		str.setPercentage((float) z);
		publisher.publish(str);
//		geometry_msgs.Twist str = publisher.newMessage();
//		geometry_msgs.Vector3 linear =  str.getLinear();
//		linear.setX(0);
//		linear.setY(0);
//		linear.setZ(d);
//		geometry_msgs.Vector3 angular =  str.getAngular();
//		angular.setX(0);
//		angular.setY(0);
//		angular.setZ(0);
//		publisher.publish(str);
	}
}
