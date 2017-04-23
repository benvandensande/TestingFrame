package com.github.drone.subb;

import java.io.IOException;

public class Gazebo {
	
	private boolean isRunning = false;
	private Runtime proc = null;
	private String path;
	
	public void initialize() throws IOException{
		String target = new String(this.path);
		this.proc = Runtime.getRuntime();
		this.proc.exec(target);
		System.out.println("Simulator is starting up");
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public void setPath(String path){
		this.path = path;
	}

	public void shutdown() {
		System.out.println("shutting down");
		this.setRunning(false);
		this.proc.exit(0);
	}

}
