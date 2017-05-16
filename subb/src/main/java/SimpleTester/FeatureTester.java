package SimpleTester;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public class FeatureTester extends Thread {

	private String feature;
	private IDrone drone;
	private Application app;

	public FeatureTester(String feature, IDrone iDrone, Application app){
		this.feature = feature;
		this.drone = iDrone;
		this.app = app;
	}

	@Override
	public void run(){
		Thread.yield();
		Class<?> clazz = null;
		Object instance = null;
		try {
			clazz = Class.forName("features."+this.getFeature());
			Constructor<?> constructor = null;
			constructor = clazz.getConstructor(IDrone.class, Application.class);
			instance = constructor.newInstance(this.drone, this.app);
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

