package SimpleTester;


import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;

import Units.Unit;
import geometry_msgs.Point;

public class RobotPosSentence extends Sentence {

	private double xPos = 0;
	private double yPos = 0;
	private double zPos = 0;
	private PositionSent sent = null;

	public RobotPosSentence(Unit i, Unit j, Unit d, PositionSent t, Application app, IUAV drone) {
		super(app,drone);
		this.xPos = i.getValue();
		this.yPos = j.getValue();
		this.zPos = d.getValue();
		this.sent = t;
	}

	public boolean runGiven() throws InterruptedException {
		Point location = this.getDrone().getLocation();
		double[] position = new double[] {location.getX(), location.getY(), location.getZ()};
		return this.sent.run(position, this.getApp(), this.xPos, this.yPos, this.zPos);
		//		new ObserverPosition(this.getDrone(), this.getApp(), this.sent, this.xPos, this.yPos, this.zPos));
//		return true;
	}

	public boolean runWhen() throws InterruptedException {
		Point location = this.getDrone().getLocation();
		double[] position = new double[] {location.getX(), location.getY(), location.getZ()};
		return this.sent.run(position, this.getApp(), this.xPos, this.yPos, this.zPos);
//		Thread t = new Thread(
//				new ObserverPosition(this.getDrone(), this.getApp(), this.sent, this.xPos, this.yPos, this.zPos));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runThen() {
		Point location = this.getDrone().getLocation();
		double[] position = new double[] {location.getX(), location.getY(), location.getZ()};
		return this.sent.run(position, this.getApp(), this.xPos, this.yPos, this.zPos);
	}
}