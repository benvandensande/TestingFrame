package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.ComponentStatus;
import com.github.drone.subb.IDrone;

public class BaroSentence extends ComponentSentence {
	
	private ComparativeSentence sent = null;

	public BaroSentence(ComparativeSentence sent, Application app, IDrone drone) {
		super(app,drone);
		this.sent = sent;
	}

	public BaroSentence(Application app, IDrone drone) {
		super(app,drone);
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

	@Override
	public boolean checkStatus(ComponentStatus status) {
		//TODO
		this.getDrone();
		return true;
		
	}

}
