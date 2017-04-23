package Observers;
import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import SimpleTester.ComparativeSentence;

public class ObserverBattery extends Observer {

	private ComparativeSentence sent = null;

	public ObserverBattery(IDrone droneInstance, Application app, ComparativeSentence sent) {
		super(droneInstance, app);
		this.sent = sent;
	}

	@Override
	public void run() {
		boolean bool = this.sent.run(drone.getBatteryPerc());
		while(!bool && application.isRunning()){
			bool = this.sent.run(drone.getBatteryPerc());
			Thread.yield();
		}
		System.out.println("BatteryPercentage: " + drone.getBatteryPerc());		
	}
}
