package com.jayanslow.projection.world.models;

import javax.vecmath.Vector3f;

public interface RealObject {

	/**
	 * Direction the object is pointing in
	 * 
	 * @return Object direction and roll
	 */
	public abstract AxisAngle4f getDirection();
	/**
	 * Unique ID of object
	 * 
	 * @return Object ID
	 */
	public int getId();

	/**
	 * Gets the position of the object (relative to the parent)
	 * 
	 * @return Position vector (in mm)
	 */
	public Vector3f getPosition();

	/**
	 * Gets the type of the object
	 * 
	 * @return Object Type
	 */
	public RealObjectType getType();
}
