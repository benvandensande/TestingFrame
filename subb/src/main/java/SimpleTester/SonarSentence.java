package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class SonarSentence extends Sentence {
	
	private ComparativeSentence sent = null;

	public SonarSentence(ComparativeSentence sent, Application app, IDrone drone) {
		super(app,drone);
		this.sent = sent;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		return this.sent.run(this.getDrone().getSonarReading());
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		return this.sent.run(this.getDrone().getSonarReading());
	}

	@Override
	public boolean runThen() {
		return this.sent.run(this.getDrone().getSonarReading());
	}

}
