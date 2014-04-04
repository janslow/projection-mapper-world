package com.jayanslow.projection.world.models;

import java.util.Collection;

import javax.vecmath.Vector3f;

public interface Universe {
	/**
	 * Gets the objects in the universe
	 * 
	 * @return Collection of objects
	 */
	public Collection<RealObject> getChildren();

	/**
	 * Gets the dimensions of the universe
	 * 
	 * @return Dimension vector (in mm)
	 */
	public Vector3f getDimensions();

	/**
	 * Sets the dimensions of the universe
	 * 
	 * dimensions Dimension vector (in mm)
	 */
	public void setDimensions(Vector3f dimensions);
}
