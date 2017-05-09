package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class RobotDistToObsSentence  extends Sentence {

	private ComparativeSentence sent = null;
	
	public RobotDistToObsSentence(ComparativeSentence sent, Application app, IDrone drone) {
		super(app,drone);
		this.sent = sent;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		double dist = getDrone().getClosestDistanceToObs();
		boolean bool = this.sent.run(dist);
		if(!bool ){
			return false;
		}
		return true;
//		Thread t = new Thread(new ObserverDistanceToObs(getDrone(), getApp(), this.sent));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		double dist = getDrone().getClosestDistanceToObs();
		boolean bool = this.sent.run(dist);
		if(!bool ){
			return false;
		}
		return true;
//		Thread t = new Thread(new ObserverDistanceToObs(getDrone(), getApp(), this.sent));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runThen() {
		double dist = getDrone().getClosestDistanceToObs();
		boolean bool = this.sent.run(dist);
		if(!bool ){
			return false;
		}
		return true;
	}
}
