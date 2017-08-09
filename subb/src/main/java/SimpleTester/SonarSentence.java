package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.ComponentStatus;
import com.github.drone.subb.IUAV;

public class SonarSentence extends ComponentSentence {
	
	private ComparativeSentence sent = null;

	public SonarSentence(ComparativeSentence sent, Application app, IUAV drone) {
		super(app,drone);
		this.sent = sent;
	}
	
	public SonarSentence(Application app, IUAV drone) {
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
		return this.getDrone().getBatteryStatus().equals(status);
	}

}
