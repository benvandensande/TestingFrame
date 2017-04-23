package com.github.drone.subb;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import geometry_msgs.Point;
import geometry_msgs.Vector3;

public class Drone implements IDrone{
	
	private DroneState state = null;
	private Point location = null;
	private List<Point> trajectory = null;
	private Map<String, String> theoriticalTrajectories = null;
	private String theoreticalTrajectory = null;
	private List<Point> takeOffTrajectory = null;
	private Vector3 velocity = null;
	private double batteryPercentage = 100;
	private boolean gpsSignal = false;
	private float sonarReading = Float.MAX_VALUE;
	private Environnement environnement = null;
	double minSpeed = 0.2;
	private String name = "quadrotor";
	
	public Drone(){
		this.state = DroneState.LANDED;
		this.trajectory = new ArrayList<Point>();
		this.takeOffTrajectory = new ArrayList<Point>();
		this.theoriticalTrajectories = new HashMap<String, String>();
		this.environnement  = new Environnement();
	}

	@Override
	public double getHeight() {
		return this.getLocation().getZ();
	}

	@Override
	public DroneState getState() {
		return state;
	}

	private void checkState() {
		double xVelocity = Math.abs(this.getXVelocity());
		double yVelocity = Math.abs(this.getYVelocity());
		double zVelocity = Math.abs(this.getZVelocity());
		if(zVelocity > minSpeed && (Math.abs(xVelocity) < minSpeed) && (Math.abs(yVelocity) < minSpeed) && (this.getState().equals(DroneState.LANDED) || this.getState().equals(DroneState.TAKEOFF))){
			if (this.getState().equals(DroneState.LANDED)){
				this.takeOffTrajectory.clear();
			}
			this.state = DroneState.TAKEOFF;
		}
		else if(xVelocity > minSpeed || yVelocity > minSpeed){
			this.state = DroneState.FLYING;
		}
		else if(zVelocity < -minSpeed && (Math.abs(xVelocity) < minSpeed) && (Math.abs(yVelocity) < minSpeed)){
			this.state = DroneState.LANDING;
		}
		else if(Math.abs(zVelocity) < minSpeed && (Math.abs(xVelocity) < minSpeed) && (Math.abs(yVelocity) < minSpeed) && (!this.getState().equals(DroneState.LANDED))){
			this.state = DroneState.HOVER;
		}
		else{
			this.state = DroneState.LANDED;
		}
		//System.out.println(this.getState());
	}
	
	@Override
	public Environnement getEnvironnement(){
		return this.environnement;
	}

	@Override
	public void setPosition(Point pos) {
		this.location = pos;
		this.addPointToTrajectory(pos);
	}

	@Override
	public Point getLocation() {
		return this.location;
	}

	@Override
	public synchronized List<Point> getTrajectory() {
		synchronized(trajectory){
			return this.trajectory;
		}
	}
	
	@Override
	public Point getTheoriticalLocation(double time) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String name = this.getTheoriticalTrajectory();
		Class<?> clazz =  Class.forName("trajectories."+ name);
		Constructor<?> constructor = clazz.getConstructor();
		ITrajectory instance = (ITrajectory) constructor.newInstance();
		return instance.Trajectory(time);
	}
	
	@Override
	public String getTheoriticalTrajectory() {
		return this.theoriticalTrajectories.get(theoreticalTrajectory);
	}

	@Override
	public void setTheoriticalTrajectory(String theoriticalTrajectory){
		this.theoreticalTrajectory = theoriticalTrajectory;
	}

	@Override
	public synchronized List<Point> getTakeOffTrajectory() {
		synchronized(takeOffTrajectory){
			return this.takeOffTrajectory;
		}
	}
	
	private synchronized void addPointToTrajectory(Point p){
		synchronized(trajectory){
			this.trajectory.add(p);
		}
		synchronized(takeOffTrajectory){
			if(this.getState().equals(DroneState.TAKEOFF))
			this.takeOffTrajectory.add(p);
		}
	}

	private Vector3 getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
		this.checkState();
	}
	
	@Override
	public double getXVelocity(){
		return this.getVelocity().getX();
	}
	
	@Override
	public double getYVelocity(){
		return this.getVelocity().getY();
	}
	
	@Override
	public double getZVelocity(){
		return this.getVelocity().getZ();
	}

	@Override
	public double getBatteryPerc() {
		return this.batteryPercentage;
	}

	@Override
	public double getAbsoluteVelocity() {
		return Math.sqrt(Math.pow(getXVelocity(), 2) + Math.pow(getYVelocity(), 2) + Math.pow(getZVelocity(), 2));
	}
	
	@Override
	public boolean hasGPSSignal(){
		return this.gpsSignal;
	}

	@Override
	public void GPSSignal(boolean serviceGps) {
		this.gpsSignal = serviceGps;
	}

	@Override
	public void SonarReading(float range) {
		this.sonarReading = range;
	}

	@Override
	public float getSonar() {
		return this.sonarReading;
	}
	
	@Override
	public void addTrajectory(String name, String call){
		this.theoriticalTrajectories.put(name,call);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getClosestDistanceToObs() {
		// TODO Auto-generated method stub
		return 0;
	}

}
