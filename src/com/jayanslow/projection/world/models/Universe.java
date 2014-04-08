package com.jayanslow.projection.world.models;

import java.util.List;

import javax.vecmath.Vector3f;

public interface Universe {
	/**
	 * Adds a real object to the collection of children
	 * 
	 * @param o
	 *            Object to add
	 * @throws NullPointerException
	 *             Thrown if o == null
	 */
	public void add(RealObject o) throws NullPointerException;

	/**
	 * Gets the objects in the universe
	 * 
	 * @return Unsorted list of objects
	 */
	public List<RealObject> getChildren();

	/**
	 * Gets the dimensions of the universe
	 * 
	 * @return Dimension vector (in mm)
	 */
	public Vector3f getDimensions();

	/**
	 * Removes a real object from the collection of children
	 * 
	 * @param o
	 *            Object to remove
	 * @return True if object was removed, otherwise false
	 */
	public boolean remove(RealObject o);

	/**
	 * Sets the dimensions of the universe
	 * 
	 * dimensions Dimension vector (in mm)
	 */
	public void setDimensions(Vector3f dimensions);
}
