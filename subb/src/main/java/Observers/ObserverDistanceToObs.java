package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import SimpleTester.ComparativeSentence;

public class ObserverDistanceToObs extends Observer{

	private ComparativeSentence sent;
	
	public ObserverDistanceToObs(IDrone droneInstance, Application app, ComparativeSentence sent) {
		super(droneInstance, app);
		this.sent = sent;
	}

	@Override
	public void run() {
		double dist = drone.getClosestDistanceToObs();
		boolean bool = this.sent.run(dist);
		while(!bool && this.application.isRunning()){
			Thread.yield();
			dist = drone.getClosestDistanceToObs();
			bool = this.sent.run(dist);
		}
		System.out.println("Distance to obstacles: " + dist);
		
	}
}