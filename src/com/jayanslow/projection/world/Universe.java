package com.jayanslow.projection.world;

import java.util.Collection;

import javax.vecmath.Vector3f;

public interface Universe extends RealObject {
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
	 * Get the display type
	 * 
	 * @return Display type
	 */
	public DisplayType getDisplayType();
}
