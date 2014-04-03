package com.jayanslow.projection.world.models;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public interface Face {

	/**
	 * Gets the direction of the face
	 * 
	 * @return Object direction and roll
	 */
	public AxisAngle4f getDirection();

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
