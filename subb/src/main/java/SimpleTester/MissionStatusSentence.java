package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.MissionStatus;

public class MissionStatusSentence extends Sentence {
	
	private MissionStatus status;
	
	public MissionStatusSentence(String st, Application app, IDrone drone){
		super(app,drone);
		this.status = MissionStatus.valueOf(st);
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		//return this.drone.getMission().getStatus().equals(this.status);
				return true;
//		Thread t = new Thread(
//				new ObserverMissionStatus(this.getDrone(), this.getApp(), this.status));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		//return this.drone.getMission().getStatus().equals(this.status);
				return true;
//		Thread t = new Thread(
//				new ObserverMissionStatus(this.getDrone(), this.getApp(), this.status));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runThen() {
		//return this.drone.getMission().getStatus().equals(this.status);
		return true;
	}

}
