package SimpleTester;

public abstract class Statement {

	protected Sentence data = null;

	public Statement(Sentence Body) {
		this.data = Body;
	}

	public Statement() {
	}

	abstract boolean run();

	abstract void addToCategory(Test test);

}