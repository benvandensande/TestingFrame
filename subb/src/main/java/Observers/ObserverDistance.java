package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import SimpleTester.ComparativeSentence;
import geometry_msgs.Point;

public class ObserverDistance extends Observer{

	private double x = 0;
	private double y = 0;
	private double z = 0;
	private ComparativeSentence sent;
	
	public ObserverDistance(IDrone droneInstance, Application app, double f, double g, double h, ComparativeSentence sent) {
		super(droneInstance, app);
		this.x = f;
		this.y = g;
		this.z = h;
		this.sent = sent;
	}

	@Override
	public void run() {
		Point location = this.drone.getLocation();
		double dist = Math.pow(location.getX() - this.x, 2) + Math.pow(location.getY() - this.y, 2) + Math.pow(location.getZ() - this.z, 2);
		boolean bool = this.sent.run(dist);
		while(!bool && this.application.isRunning()){
			Thread.yield();
			location = this.drone.getLocation();
			dist = Math.pow(location.getX() - this.x, 2) + Math.pow(location.getY() - this.y, 2) + Math.pow(location.getZ() - this.z, 2);
			bool = this.sent.run(dist);
			System.out.println(dist);
		}
		System.out.println("Distance reached: " + dist);
		
	}
}