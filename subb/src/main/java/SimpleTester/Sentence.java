package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;

public abstract class Sentence {
	
	private Application app = null;
	private IUAV drone = null;
	
	public Sentence(){
	}
	
	public Sentence(Application app, IUAV drone){
		this.setApp(app);
		this.setDrone(drone);
	}
	
	public Application getApp() {
		return app;
	}

	public IUAV getDrone() {
		return drone;
	}

	public void setDrone(IUAV drone) {
		this.drone = drone;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public abstract boolean runGiven() throws InterruptedException;

	public abstract boolean runWhen() throws InterruptedException;

	public abstract boolean runThen();
}