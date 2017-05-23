package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.ComponentStatus;
import com.github.drone.subb.IDrone;

import Units.Unit;

public class GPSSentence extends ComponentSentence {
	
	private double xPos = 0;
	private double yPos = 0;
	private double zPos = 0;
	private PositionSent sent = null;

	public GPSSentence(Unit i, Unit j, Unit d, PositionSent t, Application app, IDrone drone) {
		super(app,drone);
		this.xPos = i.getValue();
		this.yPos = j.getValue();
		this.zPos = d.getValue();
		this.sent = t;
	}

	public GPSSentence(Application app, IDrone drone) {
		super(app,drone);
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		return this.sent.run(this.getDrone().getGPSReading(), this.getApp(), this.xPos, this.yPos, this.zPos);
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		return this.sent.run(this.getDrone().getGPSReading(), this.getApp(), this.xPos, this.yPos, this.zPos);
	}

	@Override
	public boolean runThen() {
		return this.sent.run(this.getDrone().getGPSReading(), this.getApp(), this.xPos, this.yPos, this.zPos);
	}

	@Override
	public boolean checkStatus(ComponentStatus status) {
		return this.getDrone().getGPSStatus().equals(status);
	}

}
