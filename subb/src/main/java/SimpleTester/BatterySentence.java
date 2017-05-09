package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class BatterySentence extends Sentence {

	private ComparativeSentence sent = null;

	public BatterySentence(ComparativeSentence sent, Application app, IDrone drone) {
		super(app,drone);
		this.sent = sent;
	}

	public boolean runGiven() throws InterruptedException {
		return this.sent.run(this.getDrone().getBatteryPerc());
//		Thread t = new Thread(new ObserverBattery(getDrone(), getApp(), sent));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runWhen() throws InterruptedException {
		return this.sent.run(this.getDrone().getBatteryPerc());
//		Thread t = new Thread(new ObserverBattery(getDrone(), getApp(), sent));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runThen() {
		return this.sent.run(this.getDrone().getBatteryPerc());
	}
}