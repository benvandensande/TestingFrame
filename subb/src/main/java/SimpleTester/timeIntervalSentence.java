package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Units.Unit;

public class timeIntervalSentence extends Sentence {
	
	private double first;
	private double second;
	private Test test;

	public timeIntervalSentence(Unit fst, Unit scnd, Application app, IDrone drone, Test test){
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
//		Thread t = new Thread(new ObserverTime(getDrone(), getApp(), this.first, this.second));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		if(this.getApp().getSimulationTime() >= this.first && this.getApp().getSimulationTime() <= this.second){
			this.test.setFirst(this.first);
			this.test.setSecond(this.second);
			return true;
		}
		return false;
//		Thread t = new Thread(new ObserverTime(getDrone(), getApp(), this.first, this.second));
//		t.start();
//		t.join();
//		return true;
	}

	@Override
	public boolean runThen() {
		double simulationTime = this.getApp().getSimulationTime();
		System.out.println(simulationTime);
		if(simulationTime >= this.first && simulationTime <= this.second){
			return true;
		}
		return false;
	}

}
