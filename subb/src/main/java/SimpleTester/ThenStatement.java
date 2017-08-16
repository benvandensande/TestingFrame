package SimpleTester;

public class ThenStatement extends Statement {

	public ThenStatement(Sentence Body) {
		super(Body);
	}

	public ThenStatement() {
	}

	@Override
	public boolean run() {
		return this.data.runThen();
	}

	@Override
	void addToCategory(Test test) {
		test.addThen(this);
	}
}