package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.MissionRiskLevel;

public class MissionRiskLevelSentence extends Sentence {
	
	private MissionRiskLevel riskLevel;
	
	public MissionRiskLevelSentence(String risklvl, Application app, IDrone drone){
		super(app,drone);
		this.riskLevel = MissionRiskLevel.valueOf(risklvl);
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		MissionRiskLevel level = MissionRiskLevel.SAFE;//drone.getMission().getRiskLevel();
		if(!level.equals(this.riskLevel) ){
			return false;
		}
		return true;
//		Thread t = new Thread(
//				new ObserverMissionRiskLevel(this.getDrone(), this.getApp(), this.riskLevel));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		MissionRiskLevel level = MissionRiskLevel.SAFE;//drone.getMission().getRiskLevel();
		if(!level.equals(this.riskLevel)){
			return false;
		}
		return true;
//		Thread t = new Thread(
//				new ObserverMissionRiskLevel(this.getDrone(), this.getApp(), this.riskLevel));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runThen() {
		MissionRiskLevel level = MissionRiskLevel.SAFE;//drone.getMission().getRiskLevel();
		if(!level.equals(this.riskLevel)){
			return false;
		}
		return true;
	}

}
