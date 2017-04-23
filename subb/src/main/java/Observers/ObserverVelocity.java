package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;


public class ObserverVelocity extends ObserverWithLogic {

	private double maxVelocity;

	public ObserverVelocity(IDrone droneInstance, Application app, String operator, double maxVel) {
		super(droneInstance, app, operator);
		this.maxVelocity = maxVel;
	}

	@Override
	protected void runLower() {
		double velocity = drone.getAbsoluteVelocity();
		while(velocity > this.maxVelocity && application.isRunning()){
			velocity = drone.getAbsoluteVelocity();
			Thread.yield();
		}
		System.out.println("velocityTooHigh");
		
	}

	@Override
	protected void runHigher() {
		double velocity = drone.getAbsoluteVelocity();
		while(velocity < this.maxVelocity && application.isRunning()){
			velocity = drone.getAbsoluteVelocity();
			Thread.yield();
		}
		System.out.println("velocityTooHigh");
		
	}

	@Override
	protected void runEquals() {
		double velocity = drone.getAbsoluteVelocity();
		while(velocity != this.maxVelocity && application.isRunning()){
			velocity = drone.getAbsoluteVelocity();
			Thread.yield();
		}
		System.out.println("velocityTooHigh");
		
	}

}
