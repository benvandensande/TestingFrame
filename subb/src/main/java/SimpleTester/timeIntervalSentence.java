package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Observers.ObserverTime;
import Units.Unit;

public class timeIntervalSentence extends Sentence {
	
	private double first;
	private double second;
	private Application app;
	private IDrone drone;
	

	public timeIntervalSentence(Unit fst, Unit scnd, Application app, IDrone drone){
		this.first = fst.getValue();
		this.second = scnd.getValue();
		this.app = app;
		this.drone = drone;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(new ObserverTime(drone, app, this.first, this.second));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(new ObserverTime(drone, app, this.first, this.second));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runThen() {
		double simulationTime = this.app.getSimulationTime().toSeconds();
		if(simulationTime >= this.first && simulationTime <= this.second){
			return true;
		}
		return false;
	}

}
