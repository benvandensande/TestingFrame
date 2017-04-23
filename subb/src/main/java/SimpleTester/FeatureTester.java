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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getConstructor(IDrone.class, Application.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			instance = constructor.newInstance(this.drone, this.app);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		
		try {
			clazz.getMethod("run").invoke(instance);
		} catch (NoSuchMethodException | SecurityException | 
				IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		} 
	}

	private String getFeature(){
		return this.feature;
	}

}

