package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;


public class ObserverTrajectory extends Observer implements Runnable {

	private double boundary = 0;
	
	public ObserverTrajectory(IDrone droneInstance, Application app, double arg1) {
		super(droneInstance, app);
		this.boundary = arg1;
	}

	@Override
	public void run() {
		float distanceToTraj = this.calculateDistToTheoTraj();
		while(distanceToTraj < this.boundary && application.isRunning()){
			distanceToTraj = this.calculateDistToTheoTraj();
			Thread.yield();
		}
		System.out.println("Drone is lost");
		
	}

	private float calculateDistToTheoTraj() {
		try {
			return 0;
			//TODO
			//drone.getTheoriticalTrajectory().call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
