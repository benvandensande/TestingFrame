package Units;

public class Centimeter extends Unit{

	
private double val;
	
	public Centimeter(double value){
		this.val = value;
	}

	@Override
	public double getValue() {
		return val/100;
	}
}
