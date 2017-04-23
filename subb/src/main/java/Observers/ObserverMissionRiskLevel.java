package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.MissionRiskLevel;

public class ObserverMissionRiskLevel extends Observer {
	
	private MissionRiskLevel riskLevel;
	
	public ObserverMissionRiskLevel(IDrone droneInstance, Application app, MissionRiskLevel level) {
		super(droneInstance, app);
		this.riskLevel = level;
	}

	@Override
	public void run() {
		MissionRiskLevel level = MissionRiskLevel.SAFE;//drone.getMission().getRiskLevel();
		while(!level.equals(this.riskLevel) && this.application.isRunning()){
			Thread.yield();
		}
		System.out.println("Mission Risklevel is " + this.riskLevel);
	}
}
