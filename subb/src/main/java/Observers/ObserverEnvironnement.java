package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;


public class ObserverEnvironnement extends ObserverWithLogic {

	private float boundary = 0;

	public ObserverEnvironnement(IDrone droneInstance, Application app, String operator, float value) {
		super(droneInstance, app, operator);
		this.boundary = value;
	}

	@Override
	protected void runLower() {
		double dist = drone.getEnvironnement().getClosestDistanceToObject(drone.getLocation());
		while(dist > this.boundary && application.isRunning()){
			dist = drone.getEnvironnement().getClosestDistanceToObject(drone.getLocation());
			Thread.yield();
		}
		System.out.println("Environnement");
		
	}

	@Override
	protected void runHigher() {
		double dist = drone.getEnvironnement().getClosestDistanceToObject(drone.getLocation());
		while(dist < this.boundary && application.isRunning()){
			dist = drone.getEnvironnement().getClosestDistanceToObject(drone.getLocation());
			Thread.yield();
		}
		System.out.println("Environnement");
	}

	@Override
	protected void runEquals() {
		double dist = drone.getEnvironnement().getClosestDistanceToObject(drone.getLocation());
		while(dist == this.boundary && application.isRunning()){
			dist = drone.getEnvironnement().getClosestDistanceToObject(drone.getLocation());
			Thread.yield();
		}
		System.out.println("Environnement");
		
	}

}

