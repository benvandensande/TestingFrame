package SimpleTester;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.github.drone.subb.Application;
import com.github.drone.subb.IUAV;

public class FeatureTester extends Thread {

	private String feature;
	private IUAV drone;
	private Application app;
	private long timeout = 0;

	public FeatureTester(String feature, IUAV iDrone, Application app, long timeout){
		this.feature = feature;
		this.drone = iDrone;
		this.app = app;
		this.timeout = timeout;
	}

	@Override
	public void run(){
		Thread.yield();
		Class<?> clazz = null;
		Object instance = null;
		try {
			clazz = Class.forName("features."+this.getFeature());
			Constructor<?> constructor = null;
			constructor = clazz.getConstructor(IUAV.class, Application.class, long.class);
			instance = constructor.newInstance(this.drone, this.app, this.timeout);
			clazz.getMethod("run").invoke(instance);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			System.out.println("Failed to run test: " + this.getFeature() + " | TestFile not found");
		}
	}

	private String getFeature(){
		return this.feature;
	}

}

