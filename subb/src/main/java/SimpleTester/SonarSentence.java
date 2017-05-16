package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.ComponentStatus;
import com.github.drone.subb.IDrone;

public class SonarSentence extends ComponentSentence {
	
	private ComparativeSentence sent = null;

	public SonarSentence(ComparativeSentence sent, Application app, IDrone drone) {
		super(app,drone);
		this.sent = sent;
	}
	
	public SonarSentence(Application app, IDrone drone) {
		super(app,drone);
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

	@Override
	public boolean checkStatus(ComponentStatus status) {
		// TODO Auto-generated method stub
		return true;
	}

}
