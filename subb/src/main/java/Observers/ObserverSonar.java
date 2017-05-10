package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;


public class ObserverSonar extends ObserverWithLogic {

	private float boundary = 0;

	public ObserverSonar(IDrone droneInstance, Application app, String operator, float value) {
		super(droneInstance, app, operator);
		this.boundary = value;
	}

	@Override
	protected void runLower() {
		float sonar = drone.getSonarReading();
		while(sonar > this.boundary && application.isRunning()){
			sonar = drone.getSonarReading();
			Thread.yield();
		}
		System.out.println("Sonar");
		
	}

	@Override
	protected void runHigher() {
		float sonar = drone.getSonarReading();
		while(sonar < this.boundary && application.isRunning()){
			sonar = drone.getSonarReading();
			Thread.yield();
		}
		System.out.println("Sonar");
	}

	@Override
	protected void runEquals() {
		// TODO Auto-generated method stub
		
	}

}
