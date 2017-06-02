package com.github.drone.subb;

import geometry_msgs.Point;

public class Mission {
	private MissionStatus status = MissionStatus.UNACTIVE;
	private MissionRiskLevel riskLevel = MissionRiskLevel.SAFE;
	private Point goal = null;
	
	public Mission(){}
	
	public Mission(MissionStatus stat, MissionRiskLevel lev, Point gl){
		this.status = stat;
		this.riskLevel = lev;
		this.goal = gl;
	}
	
	public MissionStatus getStatus(){
		return this.status;
	}
	
	public void setStatus(MissionStatus stat){
		this.status = stat;
	}
	
	public MissionRiskLevel getRiskLevel(){
		return this.riskLevel;
	}
	
	public void setRiskLevel(MissionRiskLevel lev){
		this.riskLevel = lev;
	}
	
	public Point getGoal(){
		return this.goal;
	}
	
	public void setGoal(Point gl){
		this.goal = gl;
	}

}
