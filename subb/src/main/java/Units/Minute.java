package Units;

public class Minute extends Unit{
	private double val;
	public Minute(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val*60;
	}

}
