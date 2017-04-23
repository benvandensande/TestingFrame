package SimpleTester;

public class ThenStatement extends Statement {

	public ThenStatement(Sentence Body) {
		super(Body);
	}

	@Override
	public boolean run() {
		return this.data.runThen();
	}
}