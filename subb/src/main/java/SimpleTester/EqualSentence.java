package SimpleTester;

import Units.Unit;

public class EqualSentence extends ComparativeSentence {

	private double comparative = 0.0;

	public EqualSentence(Unit n) {
		this.comparative = n.getValue();
	}

	public boolean run(double number) {
		if (number == this.comparative)
			return true;
		return false;
	}
}
