package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;
import com.github.drone.subb.MissionStatus;

public class MissionStatusSentence extends Sentence {
	
	private MissionStatus status;
	
	public MissionStatusSentence(String st, Application app, IUAV drone){
		super(app,drone);
		this.status = MissionStatus.valueOf(st);
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		return this.getDrone().getMission().getStatus().equals(this.status);
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		return this.getDrone().getMission().getStatus().equals(this.status);
	}

	@Override
	public boolean runThen() {
		return this.getDrone().getMission().getStatus().equals(this.status);
	}

}
