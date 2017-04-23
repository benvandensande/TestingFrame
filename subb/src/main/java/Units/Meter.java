package Units;

public class Meter extends Unit{
	
	private double val;
	
	public Meter(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val;
	}

}
