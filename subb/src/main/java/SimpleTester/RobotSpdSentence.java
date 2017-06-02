package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class RobotSpdSentence extends Sentence {

	private ComparativeSentence sent = null;

	public RobotSpdSentence(ComparativeSentence i, Application app, IDrone drone) {
		super(app,drone);
		this.sent = i;
	}

	public boolean runGiven() throws InterruptedException {
		return this.sent.run(this.getDrone().getAbsoluteVelocity());
	}

	public boolean runWhen() throws InterruptedException {
		return this.sent.run(this.getDrone().getAbsoluteVelocity());
	}

	public boolean runThen() {
		return this.sent.run(this.getDrone().getAbsoluteVelocity());
	}
}