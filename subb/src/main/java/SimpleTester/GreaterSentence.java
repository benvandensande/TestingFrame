package SimpleTester;

import Units.Unit;

public class GreaterSentence extends ComparativeSentence {

	private double comparative = 0.0;

	public GreaterSentence(Unit n) {
		this.comparative = n.getValue();
	}

	public boolean run(double number) {
		if (number > this.comparative)
			return true;
		return false;
	}
}