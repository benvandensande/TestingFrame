package SimpleTester;

import java.io.IOException;
import java.util.ArrayList;

import org.ros.node.ConnectedNode;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;
import com.github.drone.subb.CoreFrameWork;
import com.github.drone.subb.textParser;

public class TesterThread extends Thread{
	
	private IUAV drone;
	private ArrayList<String> features;
	private Application app;
	private CoreFrameWork sub;
	private ConnectedNode con;
	private int startTime = 0;
	private long timeout = 0;

	public TesterThread (IUAV drone, Application app, CoreFrameWork subb, ConnectedNode con, int startTime, long timeout){
		this.drone = drone;
		this.app = app;
		this.sub = subb;
		this.con = con;
		this.startTime = startTime;
		this.features = new ArrayList<String>();
		this.timeout = timeout;
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
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		for(String feature:features){
			Thread t = new Thread(new FeatureTester(feature, this.drone, this.app,this.timeout));
			t.start();
			threads.add(t);
		}
		for(int i = 0; i < threads.size(); i++){
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("done running");
		sub.shutdown();
	}

	public void addFeature(String featureLine) {
		this.features.add(featureLine);
	}

}
