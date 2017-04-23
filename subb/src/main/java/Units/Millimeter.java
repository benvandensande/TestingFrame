package Units;

public class Millimeter extends Unit{
	
	private double val;
	
	public Millimeter(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val/1000;
	}

}
