package SimpleTester;

import com.github.drone.subb.Application;

public abstract class PositionSent {
	public abstract boolean run(double[] location, Application app, double xPos, double yPos, double zPos);
}