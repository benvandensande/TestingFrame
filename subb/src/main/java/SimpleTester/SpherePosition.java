package SimpleTester;

import com.github.drone.subb.Application;

import Units.Unit;

public class SpherePosition extends PositionSent {

		private Unit tolerance = null;

		public SpherePosition(Unit t) {
			this.tolerance = t;
		}

		public boolean run(double[] location, Application app, double x, double y, double z) {
			return (Math.pow(location[0] - x, 2) + Math.pow(location[1] - y, 2)
			+ Math.pow(location[2] - z, 2)) < Math.pow(this.tolerance.getValue(), 2);
		}
	}