package com.github.drone.subb;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import geometry_msgs.Point;
import geometry_msgs.Vector3;

public class UAV implements IUAV{
	
	private DroneState state = null;
	private Point location = null;
	private List<Point> trajectory = null;
	private Map<String, String> theoriticalTrajectories = null;
	private String theoreticalTrajectory = null;
	private List<Point> takeOffTrajectory = null;
	private Vector3 velocity = null;
	private double batteryPercentage = 100;
	private double batteryTime =0;
	private double gpsTime = 0;
	private float sonarReading = Float.MAX_VALUE;
	private double sonarTime = 0;
	private Point baroReading = null;
	private double baroTime = 0;
	private double[] GPSReading = null;
	private Environnement environnement = null;
	double minSpeed = 0.2;
	double componentTimeOut = 5000;
	private String name = "";
	private Mission mission = new Mission();
	
	public UAV(String name){
		this.name = name;
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
		else if(xVelocity > minSpeed || yVelocity > minSpeed || yVelocity > minSpeed){
			this.state = DroneState.FLYING;
		}
		else if(zVelocity > minSpeed && (Math.abs(xVelocity) < minSpeed) && (Math.abs(yVelocity) < minSpeed) && (!this.getState().equals(DroneState.LANDED) )){
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
		this.gpsTime = System.currentTimeMillis();
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
	public void BatteryPerc(double perc) {
		this.batteryTime = System.currentTimeMillis();
		this.batteryPercentage = perc;
	}

	@Override
	public double getAbsoluteVelocity() {
		return Math.sqrt(Math.pow(getXVelocity(), 2) + Math.pow(getYVelocity(), 2) + Math.pow(getZVelocity(), 2));
	}

	@Override
	public void SonarReading(float range) {
		this.sonarTime = System.currentTimeMillis();
		this.sonarReading = range;
	}

	@Override
	public float getSonarReading() {
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
		return this.getEnvironnement().getClosestDistanceToObject(this.location);
	}

	@Override
	public void BaroReading(Point point) {
		this.baroTime = System.currentTimeMillis();
		this.baroReading = point;
		
	}
	
	@Override
	public double getBaroReading() {
		return this.baroReading.getZ();
		
	}

	@Override
	public ComponentStatus getBaroStatus() {
		return checkCompTimeout(this.baroTime);
	}

	@Override
	public ComponentStatus getSonarStatus() {
		return checkCompTimeout(this.sonarTime);
	}

	@Override
	public ComponentStatus getBatteryStatus() {
		return checkCompTimeout(this.batteryTime);
	}

	@Override
	public ComponentStatus getGPSStatus() {
		return checkCompTimeout(this.gpsTime);
	}
	
	private ComponentStatus checkCompTimeout(double time) {
		if (System.currentTimeMillis() <= (time + componentTimeOut)){
			return ComponentStatus.ACTIVE;
		}else{
			return ComponentStatus.FAILING;
		}
	}

	@Override
	public void GPSReading(double alt, double l, double lat) {
		this.gpsTime = System.currentTimeMillis();
		this.setGPSReading(new double[] {lat, l, alt});
	}

	public double[] getGPSReading() {
		return GPSReading;
	}

	private void setGPSReading(double[] gPSReading) {
		GPSReading = gPSReading;
	}

	@Override
	public Mission getMission() {
		return this.mission;
	}

	@Override
	public void setMission(Mission mis) {
		this.mission = mis;
	}

}
