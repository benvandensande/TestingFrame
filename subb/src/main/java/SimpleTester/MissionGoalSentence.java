package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Observers.ObserverMissionGoal;

public class MissionGoalSentence extends Sentence{
	
	private double x = 0;
	private double y = 0;
	private double z = 0;
	private Application app = null;
	private IDrone drone = null;
	
	public MissionGoalSentence(double x, double y, double z, Application app, IDrone drone){
		this.x = x;
		this.y = y;
		this.z = z;
		this.app = app;
		this.drone = drone;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		Thread t = new Thread(
				new ObserverMissionGoal(this.drone, this.app, this.x, this.y, this.z));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		Thread t = new Thread(
				new ObserverMissionGoal(this.drone, this.app, this.x, this.y, this.z));
		t.start();
		t.join();
		return true;
	}

	@Override
	public boolean runThen() {
		double xGoal = 0;//this.drone.getMission().getX();
		double yGoal = 0;//this.drone.getMission().getY();
		double zGoal = 0;//this.drone.getMission().getZ();
		if((this.x != xGoal || this.y != yGoal || this.z != zGoal) && this.app.isRunning()){
			return false;
		}
		return true;
	}

}
