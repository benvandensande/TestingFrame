package com.github.drone.subb;

import java.util.ArrayList;
import java.util.List;

import geometry_msgs.Point;
import geometry_msgs.Vector3;

public class Environnement {
	
	private Vector3 windSpeed = null;
	private List<Object> objectsInEnvironnemnt = null;
	private List<IDrone> drones = null;
	
	public Environnement(){
		this.objectsInEnvironnemnt = new ArrayList<Object>();
		this.drones = new ArrayList<IDrone>();
	}

	public double getAbsoluteWindVelocity() {
		return 0;
		//return Math.sqrt(Math.pow(this.windSpeed.getX(), 2) + Math.pow(this.windSpeed.getY(), 2) + Math.pow(this.windSpeed.getZ(), 2));
	}

	public Vector3 getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Vector3 windSpeed) {
		this.windSpeed = windSpeed;
	}

	public List<Object> getObjectsInEnvironnemnt() {
		return objectsInEnvironnemnt;
	}

	public void addObjectToEnvironnemnt(Object p) {
		this.objectsInEnvironnemnt.add(p);
	}
	
	public void removeObjectOffEnvironnemnt(Object p) {
		this.objectsInEnvironnemnt.remove(p);
	}
	
	public double getClosestDistanceToObject(Point pos) {
		double dist = Float.MAX_VALUE;
		for( Object o: this.objectsInEnvironnemnt){
			double newDist = Math.pow(pos.getX() - o.getPosition().getX(), 2) 
					+ Math.pow(pos.getY() - o.getPosition().getY(), 2)
					+ Math.pow(pos.getZ() - o.getPosition().getZ(), 2);
			if (newDist < dist){
				dist = newDist;
			}
		}
		return dist;
	}

	public List<IDrone> getDrones() {
		return drones;
	}

	public void addDrone(IDrone drone) {
		this.drones.add(drone);
	}
	
	public void removeDrone(IDrone drone){
		this.drones.remove(drone);
	}
}
