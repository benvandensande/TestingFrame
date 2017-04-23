package com.github.drone.subb;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.parameter.ParameterTree;
import org.ros.node.topic.Publisher;

/**
 * A simple {@link Publisher} {@link NodeMain}.
 */
public class PublisherPosition extends AbstractNodeMain {

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("drone/error");
	}

	@Override
	public void onStart(final ConnectedNode connectedNode) {
		final Publisher< gazebo_msgs.ModelState> publisher =
				connectedNode.newPublisher("gazebo/set_model_state", gazebo_msgs.ModelState._TYPE);
		final Publisher< geometry_msgs.Twist> publisherVel =
				connectedNode.newPublisher("quadrotor/cmd_vel",  geometry_msgs.Twist._TYPE);
		while(!publisherVel.hasSubscribers()){}
		ParameterTree paramTree = connectedNode.getParameterTree();
		double x  = paramTree.getDouble("initialPosX");
		double y = paramTree.getDouble("initialPosY");
		double z = paramTree.getDouble("initialPosZ");
		int i = 0;
		while(i<200){
			publishVel(publisherVel, 0.3);
			publishVel(publisherVel, 0.3);
			publishVel(publisherVel, 0.0);
			i+=1;
		}
		publish(publisher, x, y, z);
		System.out.println("published");
		publish(publisher, x, y, z);
		publisher.shutdown();
		publisherVel.shutdown();
		Runtime.getRuntime().exit(0);
	}

	private void publish(final Publisher<gazebo_msgs.ModelState> publisher, double x, double y, double z) {
		gazebo_msgs.ModelState str = publisher.newMessage();
		str.setModelName("quadrotor");
		geometry_msgs.Point linear =  str.getPose().getPosition();
		str.getPose().getOrientation().setW(1);
		linear.setX(x);
		linear.setY(y);
		linear.setZ(z);
		publisher.publish(str);
	}

	private void publishVel(final Publisher<geometry_msgs.Twist> publisher, double d) {
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
