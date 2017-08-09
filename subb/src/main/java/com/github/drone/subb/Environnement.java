package com.github.drone.subb;

import java.util.ArrayList;
import java.util.List;

import geometry_msgs.Point;
import geometry_msgs.Pose;
import geometry_msgs.Vector3;

public class Environnement {
	
	private Vector3 windSpeed = null;
	private List<String> objectNames = null;
	private List<Object> objectsInEnvironnemnt = null;
	private List<IUAV> drones = null;
	
	public Environnement(){
		this.objectsInEnvironnemnt = new ArrayList<Object>();
		this.objectNames = new ArrayList<String>();
		this.drones = new ArrayList<IUAV>();
	}

	public double getAbsoluteWindVelocity() {
		if(windSpeed == null){
			return 0;
		}
		else{
			return Math.sqrt(Math.pow(this.windSpeed.getX(), 2) + Math.pow(this.windSpeed.getY(), 2) + Math.pow(this.windSpeed.getZ(), 2));
		}
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

	public void addObjectsToEnvironnemnt(List<Pose> poseLst, List<String> nameLst) {
		for(String name:nameLst.subList(1, nameLst.size())){
			if(!objectNames.contains(name) ){
				if(name.contains("quadrotor")){
					System.out.println("drone added");
					this.objectNames.add(name);
					this.addDrone(new UAV(name));
				}else{
					// TODO add size of objects
					int index = nameLst.indexOf(name);
					this.objectNames.add(name);
					this.objectsInEnvironnemnt.add(new Object(null,poseLst.get(index).getPosition()));
					System.out.println("object added: " + name );
				}
				
			}
		}
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

	public List<IUAV> getDrones() {
		return drones;
	}

	public void addDrone(IUAV drone) {
		this.drones.add(drone);
	}
	
	public void removeDrone(IUAV drone){
		this.drones.remove(drone);
	}
}
