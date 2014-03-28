package com.jayanslow.projection.scene;

import java.util.Collection;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public interface Screen extends RealObject {
	/**
	 * Gets the dimensions of the object
	 * 
	 * @return Dimension vector (in mm)
	 */
	public Vector3f getDimensions();

	/**
	 * Gets the direction the screen is pointed in
	 * 
	 * @return Direction and roll of screen
	 */
	public AxisAngle4f getDirection();

	/**
	 * Gets the face with the specified ID
	 * 
	 * @param id
	 *            Face ID
	 * @return Specified Face, or null if there is no face with specified ID
	 */
	public Face getFace(int id);

	/**
	 * Gets all the faces of this screen
	 * 
	 * @return Collection of faces
	 */
	public Collection<Face> getFaces();

	/**
	 * Unique identifier of screen
	 * 
	 * @return Screen Identifier
	 */
	public int getScreenId();

	public ScreenType getScreenType();
}
