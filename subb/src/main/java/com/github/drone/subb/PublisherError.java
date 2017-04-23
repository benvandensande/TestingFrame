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
public class PublisherError extends AbstractNodeMain {

	@Override
	public GraphName getDefaultNodeName() {
		return GraphName.of("drone/error");
	}

	@Override
	public void onStart(final ConnectedNode connectedNode) {
		final Publisher< std_msgs.String> publisher =
				connectedNode.newPublisher("errors", std_msgs.String._TYPE);
		ParameterTree paramTree = connectedNode.getParameterTree();
		std_msgs.String message = publisher.newMessage();
		message.setData(paramTree.getString("Error"));
		publisher.publish(message);
		publisher.shutdown();
		Runtime.getRuntime().exit(0);
	}
	
}
