package Units;

public class Kilometer extends Unit{
	
	
	private double val;
	
	public Kilometer(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val*1000;
	}

}
