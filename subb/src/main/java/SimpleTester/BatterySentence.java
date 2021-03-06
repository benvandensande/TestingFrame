package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.ComponentStatus;
import com.github.drone.subb.IUAV;

public class BatterySentence extends ComponentSentence {

	private ComparativeSentence sent = null;

	public BatterySentence(ComparativeSentence sent, Application app, IUAV drone) {
		super(app,drone);
		this.sent = sent;
	}
	
	public BatterySentence(Application app, IUAV drone) {
		super(app,drone);
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
		//System.out.println(this.getDrone().getBatteryPerc());
		return this.sent.run(this.getDrone().getBatteryPerc());
	}

	@Override
	public boolean checkStatus(ComponentStatus status) {
		return this.getDrone().getBatteryStatus().equals(status);
	}
}