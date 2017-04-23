package Units;

public class Hour extends Unit{
	private double val;
	public Hour(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val*3600;
	}

}
