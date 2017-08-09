package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;
import com.github.drone.subb.MissionRiskLevel;

public class MissionRiskLevelSentence extends Sentence {
	
	private MissionRiskLevel riskLevel;
	
	public MissionRiskLevelSentence(String risklvl, Application app, IUAV drone){
		super(app,drone);
		this.riskLevel = MissionRiskLevel.valueOf(risklvl);
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		MissionRiskLevel level = this.getDrone().getMission().getRiskLevel();
		if(!level.equals(this.riskLevel) ){
			return false;
		}
		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		MissionRiskLevel level = this.getDrone().getMission().getRiskLevel();
		if(!level.equals(this.riskLevel)){
			return false;
		}
		return true;
	}

	@Override
	public boolean runThen() {
		MissionRiskLevel level = this.getDrone().getMission().getRiskLevel();
		if(!level.equals(this.riskLevel)){
			return false;
		}
		return true;
	}

}
