package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class RobotPosSentence extends Sentence {

	private double xPos = 0;
	private double yPos = 0;
	private double zPos = 0;
	private PositionSent sent = null;

	public RobotPosSentence(double i, double j, double d, PositionSent t, Application app, IDrone drone) {
		super(app,drone);
		this.xPos = i;
		this.yPos = j;
		this.zPos = d;
		this.sent = t;
	}

	public boolean runGiven() throws InterruptedException {
		return this.sent.run(this.getDrone(), this.getApp(), this.xPos, this.yPos, this.zPos);
		//		new ObserverPosition(this.getDrone(), this.getApp(), this.sent, this.xPos, this.yPos, this.zPos));
//		return true;
	}

	public boolean runWhen() throws InterruptedException {
		return this.sent.run(this.getDrone(), this.getApp(), this.xPos, this.yPos, this.zPos);
//		Thread t = new Thread(
//				new ObserverPosition(this.getDrone(), this.getApp(), this.sent, this.xPos, this.yPos, this.zPos));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runThen() {
		return this.sent.run(this.getDrone(), this.getApp(), this.xPos, this.yPos, this.zPos);
	}
}