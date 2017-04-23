package SimpleTester;

public class RobotDistSentence extends Sentence {

	private Sentence sent = null;

	public RobotDistSentence(Sentence sent) {
		this.sent = sent;
	}

	public boolean runGiven() throws InterruptedException {
		return this.sent.runGiven();
	}

	public boolean runWhen() throws InterruptedException {
		return this.sent.runWhen();
	}

	public boolean runThen() {
		return this.sent.runThen();
	}
}