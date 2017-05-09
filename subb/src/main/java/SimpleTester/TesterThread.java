package SimpleTester;

import java.io.IOException;
import java.util.ArrayList;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;
import com.github.drone.subb.SubscriberDrone;
import com.github.drone.subb.textParser;

public class TesterThread extends Thread{
	
	private IDrone drone;
	private ArrayList<String> features;
	private Application app;
	private SubscriberDrone sub;

	public TesterThread (IDrone drone, Application app, SubscriberDrone subb){
		this.drone = drone;
		this.app = app;
		this.sub = subb;
		this.features = new ArrayList<String>();
		try {
			new textParser().parseTestFiles(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
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
