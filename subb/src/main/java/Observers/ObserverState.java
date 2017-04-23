package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.DroneState;
import com.github.drone.subb.IDrone;

public class ObserverState extends Observer {

	private DroneState observedState;

	public ObserverState(IDrone droneInstance, Application app, DroneState state) {
		super(droneInstance,app);
		this.observedState = state;
	}

	@Override
	public void run(){
		while(!drone.getState().equals(observedState) && this.application.isRunning()){
			Thread.yield();
		}
		System.out.println("State is " + drone.getState());
	}
}
