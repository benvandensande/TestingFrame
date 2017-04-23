package com.github.drone.subb;

import java.io.IOException;

import org.ros.message.Time;

public class Application extends Thread {
	
	private boolean isRunning = false;
	private String path = "";
	private Time simulationTime = null;
	
	@Override
	public void run(){
		try {
			Runtime.getRuntime().exec(this.path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("App starting up");
	}
	
	public boolean isRunning(){
		return this.isRunning;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public void setRunning(boolean bool){
		this.isRunning = bool;
	}

	public Time getSimulationTime() {
		return simulationTime;
	}

	public void setSimulationTime(Time time) {
		this.simulationTime = time;
	}

}
