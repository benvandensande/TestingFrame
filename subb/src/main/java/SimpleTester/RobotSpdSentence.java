package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class RobotSpdSentence extends Sentence {

	private double xSpeed = 0;
	private double ySpeed = 0;
	private double zSpeed = 0;
	private Application app = null;
	private IDrone drone = null;

	public RobotSpdSentence(double i, double j, double d, Application app, IDrone drone) {
		this.xSpeed = i;
		this.ySpeed = j;
		this.zSpeed = d;
		this.drone = drone;
		this.app = app;
	}

	public boolean runGiven() throws InterruptedException {
		return true;
	}

	public boolean runWhen() throws InterruptedException {
		return true;
	}

	public boolean runThen() {
		return true;
	}
}