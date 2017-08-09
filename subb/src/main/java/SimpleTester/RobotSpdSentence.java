package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;

public class RobotSpdSentence extends Sentence {

	private ComparativeSentence sent = null;

	public RobotSpdSentence(ComparativeSentence i, Application app, IUAV drone) {
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