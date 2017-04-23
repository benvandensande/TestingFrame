package SimpleTester;

public abstract class Statement {

	protected Sentence data = null;

	public Statement(Sentence Body) {
		this.data = Body;
	}

	abstract boolean run();

}