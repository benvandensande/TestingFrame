package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Observers.ObserverPosition;

public class RobotPosSentence extends Sentence {

	private double xPos = 0;
	private double yPos = 0;
	private double zPos = 0;
	private PositionSent sent = null;
	private Application app = null;
	private IDrone drone = null;

	public RobotPosSentence(double i, double j, double d, PositionSent t, Application app, IDrone drone) {
		this.xPos = i;
		this.yPos = j;
		this.zPos = d;
		this.sent = t;
		this.app = app;
		this.drone = drone;
	}

	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(
				new ObserverPosition(this.drone, this.app, this.sent, this.xPos, this.yPos, this.zPos));
		t.start();
		t.join();
		return true;
	}

	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(
				new ObserverPosition(this.drone, this.app, this.sent, this.xPos, this.yPos, this.zPos));
		t.start();
		t.join();
		return true;
	}

	public boolean runThen() {
		return this.sent.run(this.drone, this.app, this.xPos, this.yPos, this.zPos);
	}
}