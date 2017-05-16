package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;


public class ObserverGPS extends Observer {


	public ObserverGPS(IDrone droneInstance, Application app) {
		super(droneInstance,app);
	}
	
	@Override
	public void run(){
//		boolean hasGPS = drone.hasGPSSignal();
//		while(hasGPS && application.isRunning()){
//			hasGPS = drone.hasGPSSignal();
//			Thread.yield();
//		}
//		System.out.println("NoGPS");
	}

}
