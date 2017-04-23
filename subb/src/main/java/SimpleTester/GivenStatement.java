package SimpleTester;

public class GivenStatement extends Statement {
	
	public GivenStatement(){super(null);}

	public GivenStatement(Sentence Body) {
		super(Body);
	}

	@Override
	public boolean run() {
		try {
			return this.data.runGiven();
		} catch (Exception e) {
			return false;
		}
	}
}
