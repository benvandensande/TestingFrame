package Observers;

import org.ros.message.Time;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class ObserverTime extends Observer {
	
	private double first = 0;
	private double second = 0;

	public ObserverTime(IDrone droneInstance, Application app, double first, double second) {
		super(droneInstance, app);
		this.first = first;
		this.second = second;
	}

	@Override
	public void run() {
		boolean liesWithin = liesWithin(this.application.getSimulationTime());
		while(!liesWithin && application.isRunning()){
			liesWithin = liesWithin(this.application.getSimulationTime());
			Thread.yield();
		}
		System.out.println("In time interval: " + liesWithin);

	}

	private boolean liesWithin(double simulationTime) {
		if(simulationTime >= this.first && simulationTime <= this.second){
			return true;
		}
		return false;
	}

}
