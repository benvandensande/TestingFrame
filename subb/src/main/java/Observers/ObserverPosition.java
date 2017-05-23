package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import SimpleTester.PositionSent;

public class ObserverPosition extends Observer{

	private double x = 0;
	private double y = 0;
	private double z = 0;
	private PositionSent sent = null;
	
	public ObserverPosition(IDrone droneInstance, Application app, PositionSent sent, double g, double h, double i) {
		super(droneInstance, app);
		this.x = g;
		this.y = h;
		this.z = i;
		this.application = app;
		this.sent = sent;
	}

	@Override
	public void run() {
//		boolean liesWithin = this.sent.run(drone, this.application, x, y, z);
//		while(!liesWithin && application.isRunning()){
//			liesWithin = this.sent.run(drone, this.application, x, y, z);
//			Thread.yield();
//		}
//		System.out.println("At Position");
		
	}
}
