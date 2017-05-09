package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class MissionGoalSentence extends Sentence{
	
	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	public MissionGoalSentence(double x, double y, double z, Application app, IDrone drone){
		super(app,drone);
		this.x = x;
		this.y = y;
		this.z = z;		
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		double xGoal = 0;//this.drone.getMission().getX();
		double yGoal = 0;//this.drone.getMission().getY();
		double zGoal = 0;//this.drone.getMission().getZ();
		if((this.x != xGoal || this.y != yGoal || this.z != zGoal)){
			return false;
		}
		return true;
//		Thread t = new Thread(
//				new ObserverMissionGoal(this.getDrone(), this.getApp(), this.x, this.y, this.z));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		double xGoal = 0;//this.drone.getMission().getX();
		double yGoal = 0;//this.drone.getMission().getY();
		double zGoal = 0;//this.drone.getMission().getZ();
		if((this.x != xGoal || this.y != yGoal || this.z != zGoal)){
			return false;
		}
		return true;
//		Thread t = new Thread(
//				new ObserverMissionGoal(this.getDrone(), this.getApp(), this.x, this.y, this.z));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runThen() {
		double xGoal = 0;//this.drone.getMission().getX();
		double yGoal = 0;//this.drone.getMission().getY();
		double zGoal = 0;//this.drone.getMission().getZ();
		if((this.x != xGoal || this.y != yGoal || this.z != zGoal)){
			return false;
		}
		return true;
	}

}
