package Observers;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public abstract class ObserverWithLogic extends Observer {

	private String operator = null;

	public ObserverWithLogic(IDrone droneInstance, Application app, String op) {
		super(droneInstance, app);
		this.operator = op;
	}

	@Override
	public void run(){
		if(this.operator.equals("lower than") || this.operator.equals("closer than") ){
			runLower();
		}
		else if(this.operator.equals("greater than") || this.operator.equals("further than")){
			runHigher();
		}
		else if(this.operator.equals("equals")){
			runEquals();
		}
		else{
			System.out.println("ERROR: '" + this.operator + "' is not supported");
		}
		
	}

	protected abstract void runLower();
	protected abstract void runHigher();
	protected abstract void runEquals();

}
