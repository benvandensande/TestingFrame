package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;

public class RobotDistToObsSentence  extends Sentence {

	private ComparativeSentence sent = null;
	
	public RobotDistToObsSentence(ComparativeSentence sent, Application app, IUAV drone) {
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
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		double dist = getDrone().getClosestDistanceToObs();
		boolean bool = this.sent.run(dist);
		if(!bool ){
			return false;
		}
		return true;
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
