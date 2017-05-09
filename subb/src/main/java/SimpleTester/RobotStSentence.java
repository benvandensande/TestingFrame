package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.DroneState;
import com.github.drone.subb.IDrone;

public class RobotStSentence extends Sentence {

	private String state = "";

	public RobotStSentence(String state, Application app, IDrone drone) {
		super(app,drone);
		this.state = state;
	}

	public boolean runGiven() throws InterruptedException {
		return getDrone().getState().equals(DroneState.valueOf(this.state));
//		Thread t = new Thread(new ObserverState(getDrone(), getApp(), DroneState.valueOf(this.state)));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runWhen() throws InterruptedException {
		return getDrone().getState().equals(DroneState.valueOf(this.state));
//		System.out.println("State becomes " + DroneState.valueOf(this.state));
//		Thread t = new Thread(new ObserverState(getDrone(), getApp(), DroneState.valueOf(this.state)));
//		t.start();
//		t.join();
//		return true;
	}

	public boolean runThen() {
		System.out.println(getDrone().getState());
		return getDrone().getState().equals(DroneState.valueOf(this.state));
//		if (getApp().isRunning()) {
//			System.out.println(getDrone().getState());
//			return getDrone().getState().equals(DroneState.valueOf(this.state));
//		} else {
//			System.out.println("app was done");
//			return true;
//		}
	}
}