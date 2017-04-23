package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Observers.ObserverWind;

public class WindSpdSentence extends Sentence {

	private ComparativeSentence sent = null;
	private Application app = null;
	private IDrone drone = null;

	public WindSpdSentence(ComparativeSentence i, Application app, IDrone drone) {
		this.sent = i;
		this.drone = drone;
		this.app = app;
	}

	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(new ObserverWind(drone, app, sent));
		t.start();
		t.join();
		return true;
	}

	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(new ObserverWind(drone, app, sent));
		t.start();
		t.join();
		return true;
	}

	public boolean runThen() {
		return sent.run(drone.getEnvironnement().getAbsoluteWindVelocity());
	}
}