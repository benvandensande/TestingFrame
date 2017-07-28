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
	private long timeOut =0;
	private long beginTime = 0;
	private boolean printed = false;;

	public Test(String name, String description, Application app, long t) {
		this.name = name;
		this.description = description;
		this.app = app;
		this.timeOut = t *1000;
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
		this.beginTime = System.currentTimeMillis();
		boolean result = checkStatements(this.givenStatements);
		System.out.println(result);
		if(result) {
			System.out.println("in when");
			System.out.println(this.timeOut);
			result = checkStatements(this.whenStatements);
			if(result) {
				if(Double.isNaN(this.first)){
					System.out.println("in then");
					result = checkThenStatements(result);
				}else if (this.first == Double.MAX_VALUE){
					System.out.println("in then always time");
					while(result && this.app.isRunning() && !checkTimeout()){
						result = checkThenStatements(result);
						try {
							Test.sleep(25);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}else if (this.first == Double.MIN_VALUE){
					System.out.println("in then never time");
					while(result && this.app.isRunning() && !checkTimeout()){
						result = !checkThenStatements(result);
						try {
							Test.sleep(25);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}else{
					System.out.println("in then long");
					while(result && checkBoundaries() && this.app.isRunning() && !checkTimeout()){
						result = checkThenStatements(result);
						try {
							Test.sleep(25);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		if(!this.printed) System.out.println("Ran test: " + this.name + "| Successfull: " + result);
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
	
	private boolean checkStatements(List<Statement> statements) {
		if(checkTimeout()){
			return false;
		}
		else if(statements.isEmpty()){
			return true;
		}else{
			boolean allTrue = false;
			while(!allTrue && app.isRunning() && !checkTimeout()){
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
		if(!app.isRunning() || checkTimeout()){
			return false;
		}
		else{
			for (Statement stat : this.thenStatements) {
				if (stat.run() == false) {
					result = false;
				}
			}
			return result;
		}
	}
	
	private boolean checkBoundaries(){
		double simulationTime = this.app.getSimulationTime();
		if(simulationTime >= this.first && simulationTime <= this.second){
			return true;
		}
		return false;
	}
	
	private boolean checkTimeout(){
		if((System.currentTimeMillis() - this.beginTime) > this.timeOut){
			System.out.println("Ran test: " + this.name + "| Test Timeout");
			this.printed = true;
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
