package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;


public abstract class Observer extends Thread {

	protected IDrone drone = null;
	protected Application application = null;

	public Observer(IDrone droneInstance, Application app){
		this.setDrone(droneInstance);
		this.application = app;
	}

	private void setDrone(IDrone drone) {
		this.drone = drone;
	}
	
	@Override
	public abstract void run();
}
