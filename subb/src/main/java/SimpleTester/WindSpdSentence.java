package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class WindSpdSentence extends Sentence {

	private ComparativeSentence sent = null;

	public WindSpdSentence(ComparativeSentence i, Application app, IDrone drone) {
		super(app,drone);
		this.sent = i;
	}

	public boolean runGiven() throws InterruptedException {
		return sent.run(getDrone().getEnvironnement().getAbsoluteWindVelocity());
//		Thread t = new Thread(new ObserverWind(getDrone(), getApp(), sent));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runWhen() throws InterruptedException {
		return sent.run(getDrone().getEnvironnement().getAbsoluteWindVelocity());
//		Thread t = new Thread(new ObserverWind(getDrone(), getApp(), sent));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runThen() {
		return sent.run(getDrone().getEnvironnement().getAbsoluteWindVelocity());
	}
}