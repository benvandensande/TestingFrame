package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;

public class WindSpdSentence extends Sentence {

	private ComparativeSentence sent = null;

	public WindSpdSentence(ComparativeSentence i, Application app, IUAV drone) {
		super(app,drone);
		this.sent = i;
	}

	public boolean runGiven() throws InterruptedException {
		return sent.run(getDrone().getEnvironnement().getAbsoluteWindVelocity());
	}

	public boolean runWhen() throws InterruptedException {
		return sent.run(getDrone().getEnvironnement().getAbsoluteWindVelocity());
	}

	public boolean runThen() {
		return sent.run(getDrone().getEnvironnement().getAbsoluteWindVelocity());
	}
}