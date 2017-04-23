package SimpleTester;

import com.github.drone.subb.Application;
import com.github.drone.subb.IDrone;

import Units.Unit;
import geometry_msgs.Point;

public class CirclePosition extends PositionSent {

	private Unit tolerance = null;

	public CirclePosition(Unit t, String unit) {
		this.tolerance = t;
	}

	public boolean run(IDrone drone, Application app, double x, double y, double z) {
		Point location = drone.getLocation();
		return (Math.pow(location.getX() - x, 2) + Math.pow(location.getY() - y, 2)) 
				< Math.pow(this.tolerance.getValue(), 2);
	}
}