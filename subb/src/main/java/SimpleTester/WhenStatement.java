package SimpleTester;

public class WhenStatement extends Statement {

	public WhenStatement(Sentence Body) {
		super(Body);
	}
	
	public WhenStatement() {
	}

	@Override
	public boolean run() {
		try {
			return this.data.runWhen();
		} catch (InterruptedException e) {
			return false;
		}
	}

	@Override
	void addToCategory(Test test) {
		test.addWhen(this);
		
	}
}
