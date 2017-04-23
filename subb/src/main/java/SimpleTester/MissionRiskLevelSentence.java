package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.MissionRiskLevel;

import Observers.ObserverMissionRiskLevel;

public class MissionRiskLevelSentence extends Sentence {
	
	private MissionRiskLevel riskLevel;
	private Application app = null;
	private IDrone drone = null;
	
	public MissionRiskLevelSentence(String risklvl, Application app, IDrone drone){
		this.riskLevel = MissionRiskLevel.valueOf(risklvl);
		this.app = app;
		this.drone = drone;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(
				new ObserverMissionRiskLevel(this.drone, this.app, this.riskLevel));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(
				new ObserverMissionRiskLevel(this.drone, this.app, this.riskLevel));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runThen() {
		MissionRiskLevel level = MissionRiskLevel.SAFE;//drone.getMission().getRiskLevel();
		if(!level.equals(this.riskLevel) && this.app.isRunning()){
			return false;
		}
		return true;
	}

}
