package Units;

public class Second extends Unit{
	private double val;
	public Second(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val;
	}
}

