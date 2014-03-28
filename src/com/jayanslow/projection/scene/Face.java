package com.jayanslow.projection.scene;

import javax.vecmath.Vector3f;

public interface Face {
	/**
	 * Gets the dimensions of the face (from the screen)
	 * 
	 * @return Vector representing dimensions
	 */
	public Vector3f getDimensions();

	/**
	 * Gets the id of the face, such that (parent, faceId) is unique
	 * 
	 * @return Face id
	 */
	public int getFaceId();

	/**
	 * Gets the position
	 * 
	 * @return
	 */
	public Vector3f getPosition();
}
