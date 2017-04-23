package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.MissionStatus;

public class ObserverMissionStatus extends Observer {
	
	private MissionStatus state;

	public ObserverMissionStatus(IDrone droneInstance, Application app, MissionStatus state) {
		super(droneInstance, app);
		this.state = state;
	}

	@Override
	public void run() {
		while(!drone.getState().equals(this.state) && this.application.isRunning()){
			Thread.yield();
		}
		System.out.println("Mission State is " + this.state);
	}

}
