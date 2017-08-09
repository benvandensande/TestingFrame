package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;

import Units.Unit;

public class timeIntervalSentence extends Sentence {
	
	private double first;
	private double second;
	private Test test;

	public timeIntervalSentence(Unit fst, Unit scnd, Application app, IUAV drone, Test test){
		super(app,drone);
		this.first = fst.getValue();
		this.second = scnd.getValue();
		this.test = test;
	}

	@Override
	public boolean runGiven() throws InterruptedException {
		
		if(this.getApp().getSimulationTime() >= this.first && this.getApp().getSimulationTime() <= this.second){
			return true;
		}
		return false;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		if(this.getApp().getSimulationTime() >= this.first && this.getApp().getSimulationTime() <= this.second){
			this.test.setFirst(this.first);
			this.test.setSecond(this.second);
			return true;
		}
		return false;
	}

	@Override
	public boolean runThen() {
		double simulationTime = this.getApp().getSimulationTime();
		if(simulationTime >= this.first && simulationTime <= this.second){
			return true;
		}
		return false;
	}

}
