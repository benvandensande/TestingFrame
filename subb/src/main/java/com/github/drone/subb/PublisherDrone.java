package com.github.drone.subb;

import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;

import std_msgs.String;

/**
 * A simple {@link Publisher} {@link NodeMain}.
 */
public class PublisherDrone extends AbstractNodeMain {
	
	private boolean running = false;

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("drone/talker");
	}

	@Override
	public void onStart(final ConnectedNode connectedNode) {
		final Publisher<std_msgs.String> applicationPub = 
				connectedNode.newPublisher("/application", std_msgs.String._TYPE);
		final Subscriber<std_msgs.String> sub = 
				connectedNode.newSubscriber("/application", std_msgs.String._TYPE);
		sub.addMessageListener(new MessageListener<std_msgs.String>() {

			@Override
			public void onNewMessage(std_msgs.String message) {
				if(message.getData().equals("run") && !running){
					running = true;
					System.out.println("message received: " + message.getData());
					final Publisher< geometry_msgs.Twist> publisher =
							connectedNode.newPublisher("quadrotor/cmd_vel",  geometry_msgs.Twist._TYPE);
					int i = 0;
					while(i<1000){
						publish(publisher, 0.3);
						System.out.println("published");
						publish(publisher, 0.3);
						i += 1;
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i = 0;
					while(i<200){
						publish(publisher, 0);
						System.out.println("stopped");
						publish(publisher, 0);
						i += 1;
					}
					publish(applicationPub, "stopped");
					Runtime.getRuntime().exit(0);
				}
			}
		});
		
		while(!running){
			System.out.println("message send");
			publish(applicationPub, "started");
		}
		
		
}

	private void publish(Publisher<String> applicationPub, java.lang.String string) {
		std_msgs.String str = applicationPub.newMessage();
		str.setData(string);
		applicationPub.publish(str);
	}

	private void publish(final Publisher<geometry_msgs.Twist> publisher, double d) {
		geometry_msgs.Twist str = publisher.newMessage();
		geometry_msgs.Vector3 linear =  str.getLinear();
		linear.setX(0);
		linear.setY(0);
		linear.setZ(d);
		geometry_msgs.Vector3 angular =  str.getAngular();
		angular.setX(0);
		angular.setY(0);
		angular.setZ(0);
		publisher.publish(str);
	}
}
