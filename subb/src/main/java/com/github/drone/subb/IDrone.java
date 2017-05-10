package com.github.drone.subb;

import geometry_msgs.Point;
import geometry_msgs.Vector3;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IDrone {
	public double getHeight();
	public DroneState getState();
	public void setPosition(Point pos);
	public Point getLocation();
	public List<Point> getTrajectory();
	public String getTheoriticalTrajectory();
	public void setTheoriticalTrajectory(String arg1) throws IOException;
	public void setVelocity(Vector3 vector3) ;
	public double getXVelocity();
	public double getYVelocity();
	public double getZVelocity();
	public double getBatteryPerc();
	public double getAbsoluteVelocity();
	public boolean hasGPSSignal();
	public void GPSSignal(boolean serviceGps);
	public void SonarReading(float range);
	public float getSonarReading();
	List<Point> getTakeOffTrajectory();
	public void addTrajectory(String name, String call);
	public Environnement getEnvironnement();
	Point getTheoriticalLocation(double time) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	public String getName();
	public void setName(String name);
	public double getClosestDistanceToObs();
	public void BaroReading(Point point);
	public double getBaroReading();
}
