package com.github.drone.subb;

import java.util.Map;

public class Configuration {
	
	private Map<String, String> topics;
	private String simulatorbashpath;
	private String applicationbashpath;
	
	
	public Map<String, String> getTopics(){
		return topics;
	}
	
	public void setTopics(Map<String, String> topics){
		this.topics = topics;
	}

	public String getVelocity() {
		return this.getTopics().get("velocity");
	}


	public String getLocation() {
		return this.getTopics().get("position");
	}
	
	public String getApplicationStart() {
		return this.getTopics().get("applicationStart");
	}
	
	public String getApplicationStop() {
		return this.getTopics().get("applicationStop");
	}
	
	public String getSonar() {
		return this.getTopics().get("sonar");
	}
	
	public String getSimulator() {
		return this.getTopics().get("simulator");
	}
	
	public String getError() {
		return this.getTopics().get("error");
	}
	public String getBarometer() {
		return this.getTopics().get("barometer");
	}
	
	public String getGPS() {
		return this.getTopics().get("gps");
	}
	
	@Override
	public String toString(){
		return this.getVelocity().toString();
	}
	
	public void setApplicationbashpath(String bash){
		this.applicationbashpath = bash;
	}

	public String getApplicationbashpath() {
		return applicationbashpath;
	}
	
	public void setSimulatorbashpath(String bash){
		this.simulatorbashpath = bash;
	}
	
	public String getSimulatorbashpath() {
		return simulatorbashpath;
	}

	

	
	
	
	
	
}
