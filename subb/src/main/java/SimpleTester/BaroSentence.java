package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class BaroSentence extends Sentence {
	
	private ComparativeSentence sent = null;

	public BaroSentence(ComparativeSentence sent, Application app, IDrone drone) {
		super(app,drone);
		this.sent = sent;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		return this.sent.run(this.getDrone().getBaroReading());
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		return this.sent.run(this.getDrone().getBaroReading());
	}

	@Override
	public boolean runThen() {
		return this.sent.run(this.getDrone().getBaroReading());
	}

}
