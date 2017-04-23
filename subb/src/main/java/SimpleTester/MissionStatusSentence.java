package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.MissionStatus;

import Observers.ObserverMissionStatus;

public class MissionStatusSentence extends Sentence {
	
	private MissionStatus status;
	private Application app = null;
	private IDrone drone = null;
	
	public MissionStatusSentence(String st, Application app, IDrone drone){
		this.status = MissionStatus.valueOf(st);
		this.app = app;
		this.drone = drone;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(
				new ObserverMissionStatus(this.drone, this.app, this.status));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(
				new ObserverMissionStatus(this.drone, this.app, this.status));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runThen() {
		//return this.drone.getMission().getStatus().equals(this.status);
		return true;
	}

}
