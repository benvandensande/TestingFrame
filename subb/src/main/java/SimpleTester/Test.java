package SimpleTester;

import java.util.ArrayList;
import java.util.List;

import com.github.drone.subb.Application;

public class Test extends Thread {
	private String name = "";
	private String description = "";
	private Application app = null;
	private List<Statement> statements = new ArrayList<Statement>();
	private List<Statement> givenStatements = new ArrayList<Statement>();
	private List<Statement> whenStatements = new ArrayList<Statement>();
	private List<Statement> thenStatements = new ArrayList<Statement>();
	private double first = Double.NaN;
	private double second = Double.NaN;
	//TODO maximum time for running test for recovery application crash

	public Test(String name, String description, Application app) {
		this.name = name;
		this.description = description;
		this.app = app;
	}
	
	public void commitStatements(List<Statement> statements){
		this.statements = statements;
		scanStatements();
	}
	
	public String getDescription(){
		return this.description;
	}
	
	private void scanStatements(){
		for (Statement stat: this.statements) {
			stat.addToCategory(this);
		}
	}

	public void run() {
		boolean result = checkStatement(this.givenStatements);
		if(result) {
			System.out.println("in when");
			result = checkStatement(this.whenStatements);
			if(result) {
				if(Double.isNaN(this.first)){
					System.out.println("in then");
					result = checkThenStatements(result);
				}else{
					System.out.println("in then long");
					while(result && checkBoundaries() && this.app.isRunning()){
						//TODO add little sleep(25milliseconds)
						result = checkThenStatements(result);
					}
				}
			}
		}
		System.out.println("Ran test: " + this.name + "| Successfull: " + result);
//		boolean result = true;
//		for (Statement stat : this.statements) {
//			if (stat.run() == false) {
//				result = false;
//			}
//		}
//		System.out.println("Ran test: " + this.name + "| Successfull: " + result);
	}

	

	public void addGiven(GivenStatement givenStatement) {
		this.givenStatements.add(givenStatement);
	}

	public void addWhen(WhenStatement whenStatement) {
		this.whenStatements.add(whenStatement);
	}

	public void addThen(ThenStatement thenStatement) {
		this.thenStatements.add(thenStatement);
	}	
	
	private boolean checkStatement(List<Statement> statements) {
		if(statements.isEmpty()){
			return true;
		}else{
			boolean allTrue = false;
			while(!allTrue && app.isRunning()){
				allTrue = true;
				for (Statement stat : statements) {
					if (stat.run() == false) {
						allTrue = false;
					}
				}
			}
			return allTrue;
		}
		
	}
	
	private boolean checkThenStatements(boolean result) {
		for (Statement stat : this.thenStatements) {
			if (stat.run() == false) {
				result = false;
			}
		}
		return result;
	}
	
	private boolean checkBoundaries(){
		
		double simulationTime = this.app.getSimulationTime();
		if(simulationTime >= this.first && simulationTime <= this.second){
			return true;
		}
		return false;
	}

	public void setFirst(double f) {
		this.first = f;
	}

	public void setSecond(double s) {
		this.second = s;
	}
		
}
