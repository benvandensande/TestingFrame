package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.DroneState;
import com.github.drone.subb.IDrone;

import Observers.ObserverState;

public class RobotStSentence extends Sentence {

	private String state = "";
	private Application app = null;
	private IDrone drone = null;

	public RobotStSentence(String state, Application app, IDrone drone) {
		this.state = state;
		this.drone = drone;
		this.app = app;
	}

	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(new ObserverState(drone, app, DroneState.valueOf(this.state)));
		t.start();
		t.join();
		return true;
	}

	public boolean runWhen() throws InterruptedException {
		System.out.println("State becomes " + DroneState.valueOf(this.state));
		Thread t = new Thread(new ObserverState(drone, app, DroneState.valueOf(this.state)));
		t.start();
		t.join();
		return true;
	}

	public boolean runThen() {
		if (app.isRunning()) {
			return drone.getState().equals(DroneState.valueOf(this.state));
		} else {
			System.out.println("app was done");
			return true;
		}
	}
}