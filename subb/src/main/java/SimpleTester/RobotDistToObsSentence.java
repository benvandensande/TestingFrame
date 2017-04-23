package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Observers.ObserverDistanceToObs;

public class RobotDistToObsSentence  extends Sentence {

	private ComparativeSentence sent = null;
	private Application app = null;
	private IDrone drone = null;
	public RobotDistToObsSentence(ComparativeSentence sent, Application app, IDrone drone) {
		this.sent = sent;
		this.drone = drone;
		this.app = app;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(new ObserverDistanceToObs(drone, app, this.sent));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(new ObserverDistanceToObs(drone, app, this.sent));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runThen() {
		double dist = drone.getClosestDistanceToObs();
		boolean bool = this.sent.run(dist);
		if(!bool && this.app.isRunning()){
			return false;
		}
		return true;
	}
}
