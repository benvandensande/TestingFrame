package SimpleTester;

import java.io.IOException;
import java.util.ArrayList;

import org.ros.node.ConnectedNode;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.SubscriberDrone;
import com.github.drone.subb.textParser;

public class TesterThread extends Thread{
	
	private IDrone drone;
	private ArrayList<String> features;
	private Application app;
	private SubscriberDrone sub;
	private ConnectedNode con;
	private int startTime = 0;

	public TesterThread (IDrone drone, Application app, SubscriberDrone subb, ConnectedNode con, int startTime){
		this.drone = drone;
		this.app = app;
		this.sub = subb;
		this.con = con;
		this.startTime = startTime;
		this.features = new ArrayList<String>();
		try {
			new textParser().parseTestFiles(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		while(this.con.getCurrentTime().secs < startTime){}
		System.out.println("Testing Framework started");
		for(String feature:features){
			Thread t = new Thread(new FeatureTester(feature, this.drone, this.app));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			;
		}
		System.out.println("done running");
		sub.shutdown();
	}

	public void addFeature(String featureLine) {
		this.features.add(featureLine);
	}

}
