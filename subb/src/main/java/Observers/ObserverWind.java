package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import SimpleTester.ComparativeSentence;


public class ObserverWind extends Observer {

	private ComparativeSentence sent = null;

	public ObserverWind(IDrone droneInstance, Application app, ComparativeSentence sent) {
		super(droneInstance, app);
		this.sent = sent;
	}

	@Override
	public void run() {
		while(this.application.isRunning() && !this.sent.run(drone.getEnvironnement().getAbsoluteWindVelocity())){
			Thread.yield();
		}
		System.out.println("Windspeed reached: " + this.drone.getEnvironnement().getAbsoluteWindVelocity());
	}
	

}
