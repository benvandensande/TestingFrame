package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Observers.ObserverDistance;
import geometry_msgs.Point;

public class DistanceToLocSentence extends Sentence {
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	private ComparativeSentence sent = null;

	public DistanceToLocSentence(double xPos, double yPos, double zPos, ComparativeSentence sent, Application app,
			IDrone drone) {
		super(app,drone);
		this.x = xPos;
		this.y = yPos;
		this.z = zPos;
		this.sent = sent;
	}

	public boolean runGiven() throws InterruptedException {
		Point location = this.getDrone().getLocation();
		Double dist = Math.pow(location.getX() - this.x, 2) + Math.pow(location.getY() - this.y, 2)
		+ Math.pow(location.getZ() - this.z, 2);
		return this.sent.run(dist);
//		Thread t = new Thread(new ObserverDistance(getDrone(), getApp(), this.x, this.y, this.z, this.sent));
//		t.start();
//		t.join();
//		return true;
	}

	// TODO
	public boolean runWhen() throws InterruptedException {
		Point location = this.getDrone().getLocation();
		Double dist = Math.pow(location.getX() - this.x, 2) + Math.pow(location.getY() - this.y, 2)
		+ Math.pow(location.getZ() - this.z, 2);
		return this.sent.run(dist);
//		Thread t = new Thread(new ObserverDistance(getDrone(), getApp(), this.x, this.y, this.z, this.sent));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runThen() {
		Point location = this.getDrone().getLocation();
		Double dist = Math.pow(location.getX() - this.x, 2) + Math.pow(location.getY() - this.y, 2)
		+ Math.pow(location.getZ() - this.z, 2);
		return this.sent.run(dist);
	}
}