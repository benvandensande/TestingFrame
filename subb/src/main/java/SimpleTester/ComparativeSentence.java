package SimpleTester;

public abstract class ComparativeSentence {

	public abstract boolean run(double number);

	public double convertUnit(double x, String name) {
		if (name == null) {
			return x;
		}
		if (name.equals("centimeter")) {
			return x / 100;
		} else if (name.equals("kilometer")) {
			return x * 100;
		} else if (name.equals("millimeter")) {
			return x / 1000;
		} else
			return x;
	}
}
