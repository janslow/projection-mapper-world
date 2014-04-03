package com.jayanslow.projection.world.models;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public interface RealObject {

	/**
	 * Direction the object is pointing in
	 * 
	 * @return Object direction and roll
	 */
	public abstract AxisAngle4f getDirection();

	/**
	 * Sets the direction the object is facing
	 * 
	 * @param direction
	 *            New direction (must not be null)
	 * @throws NullPointerException
	 *             Thrown if direction is null
	 */
	public abstract void setDirection(AxisAngle4f direction) throws NullPointerException;

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
	 * Sets the position of the object (relative to the parent)
	 * 
	 * @param position
	 *            New position
	 * @throws NullPointerException
	 *             Thrown if position == null
	 */
	public void setPosition(Vector3f position) throws NullPointerException, UnsupportedOperationException;

	/**
	 * Gets the type of the object
	 * 
	 * @return Object Type
	 */
	public RealObjectType getType();
}
