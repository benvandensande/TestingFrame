package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Observers.ObserverBattery;

public class BatterySentence extends Sentence {

	private ComparativeSentence sent = null;
	private Application app = null;
	private IDrone drone = null;

	public BatterySentence(ComparativeSentence sent, Application app, IDrone drone) {
		this.sent = sent;
		this.drone = drone;
		this.app = app;
	}

	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(new ObserverBattery(drone, app, sent));
		t.start();
		t.join();
		return true;
	}

	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(new ObserverBattery(drone, app, sent));
		t.start();
		t.join();
		return true;
	}

	public boolean runThen() {
		return this.sent.run(this.drone.getBatteryPerc());
	}
}