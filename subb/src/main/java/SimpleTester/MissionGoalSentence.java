package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Units.Unit;

public class MissionGoalSentence extends Sentence{
	
	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	public MissionGoalSentence(Unit x, Unit y, Unit z, Application app, IDrone drone){
		super(app,drone);
		this.x = x.getValue();
		this.y = y.getValue();
		this.z = z.getValue();		
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		double xGoal = this.getDrone().getMission().getGoal().getX();
		double yGoal = this.getDrone().getMission().getGoal().getY();
		double zGoal = this.getDrone().getMission().getGoal().getZ();
		if((this.x != xGoal || this.y != yGoal || this.z != zGoal)){
			return false;
		}
		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		double xGoal = this.getDrone().getMission().getGoal().getX();
		double yGoal = this.getDrone().getMission().getGoal().getY();
		double zGoal = this.getDrone().getMission().getGoal().getZ();
		if((this.x != xGoal || this.y != yGoal || this.z != zGoal)){
			return false;
		}
		return true;
	}

	@Override
	public boolean runThen() {
		double xGoal = this.getDrone().getMission().getGoal().getX();
		double yGoal = this.getDrone().getMission().getGoal().getY();
		double zGoal = this.getDrone().getMission().getGoal().getZ();
		if((this.x != xGoal || this.y != yGoal || this.z != zGoal)){
			return false;
		}
		return true;
	}

}
