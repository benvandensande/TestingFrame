package com.github.drone.subb;

import geometry_msgs.Point;

public class Object {
	
	private int[] size = null;
	private Point position = null;
	
	public Object(int[] size, Point pos){
		this.setSize(size);
		this.setPosition(pos);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int[] getSize() {
		return size;
	}

	public void setSize(int[] size) {
		this.size = size;
	}

}
