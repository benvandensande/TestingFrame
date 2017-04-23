package SimpleTester;

import java.util.ArrayList;
import java.util.List;

public class Test extends Thread {
	private String name = "";
	private String description = "";
	private List<Statement> statements = new ArrayList<Statement>();

	public Test(String name, String description, List<Statement> statements) {
		this.name = name;
		this.description = description;
		this.statements = statements;
	}
	
	public String getDescription(){
		return this.description;
	}

	public void run() {
		boolean result = true;
		for (Statement stat : this.statements) {
			if (stat.run() == false) {
				result = false;
			}
		}
		System.out.println("Ran test: " + this.name + "| Successfull: " + result);
	}	
}
