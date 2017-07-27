package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class TimeSentence extends Sentence{
	
	private Test test = null;
	private double value = 0;

	
	public TimeSentence(double val, Application app, IDrone drone, Test test){
		super(app,drone);
		this.test = test;
		this.value = val;
	}	

	@Override
	public boolean runGiven() throws InterruptedException {
		return false;
	}

	@Override
	public boolean runWhen() throws InterruptedException {
		this.test.setFirst(this.value);
		return true;
	}

	@Override
	public boolean runThen() {
		return false;
	}
}
