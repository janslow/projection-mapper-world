package com.jayanslow.projection.world.models;

import javax.vecmath.Vector3f;

public interface Face {

	/**
	 * Gets the id of the face, such that (parent, faceId) is unique
	 * 
	 * @return Face id
	 */
	public int getFaceId();

	/**
	 * Friendly name of face
	 * 
	 * @return Friendly name, or null
	 */
	public String getName();

	/**
	 * Gets the position
	 * 
	 * @return
	 */
	public Vector3f getPosition();

	/**
	 * Gets the direction of the face
	 * 
	 * @return Object direction and roll
	 */
	public Rotation3f getRotation();

	/**
	 * Gets the screen which this face is part of
	 * 
	 * @return Parent Screen
	 */
	public Screen getScreen();
}
