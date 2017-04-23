package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class ObserverMissionGoal extends Observer {
	
	private double x = 0;
	private double y = 0;
	private double z = 0;
	public ObserverMissionGoal(IDrone droneInstance, Application app, double x, double y, double z) {
		super(droneInstance, app);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void run() {
		double xGoal = 0;//this.drone.getMission().getX();
		double yGoal = 0;//this.drone.getMission().getY();
		double zGoal = 0;//this.drone.getMission().getZ();
		while((this.x != xGoal || this.y != yGoal || this.z != zGoal) && this.application.isRunning()){
			Thread.yield();
		}
		System.out.println("Mission goal is equal");

	}

}
