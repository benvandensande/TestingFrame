package Units;

public class Percent extends Unit{
	private double val;
	public Percent(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val;
	}
}
