package SimpleTester;

public abstract class Sentence {
	public abstract boolean runGiven() throws InterruptedException;

	public abstract boolean runWhen() throws InterruptedException;

	public abstract boolean runThen();
}