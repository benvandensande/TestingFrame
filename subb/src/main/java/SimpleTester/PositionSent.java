package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

public abstract class PositionSent {
	public abstract boolean run(double[] location, Application app, double xPos, double yPos, double zPos);
}