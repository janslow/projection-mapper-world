package com.jayanslow.projection.world.models;

import javax.vecmath.Vector3f;

public interface RealObject {

	/**
	 * Rotation of the object
	 * 
	 * @return Object rotations
	 */
	public abstract Rotation3f getRotation();

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

	/**
	 * Sets the rotation of the object
	 * 
	 * @param rotation
	 *            New rotations (must not be null)
	 * @throws NullPointerException
	 *             Thrown if direction is null
	 */
	public abstract void setRotation(Rotation3f rotation) throws NullPointerException;

	/**
	 * Sets the position of the object (relative to the parent)
	 * 
	 * @param position
	 *            New position
	 * @throws NullPointerException
	 *             Thrown if position == null
	 */
	public void setPosition(Vector3f position) throws NullPointerException, UnsupportedOperationException;
}
