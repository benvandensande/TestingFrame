package Units;

public class MS extends Unit{
	private double x;
	private double y;
	private double z;
	public MS(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double getValue() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2))*3600;
	}


}
