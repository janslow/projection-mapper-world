package com.jayanslow.projection.world.models;

import javax.vecmath.Vector3f;

import com.jayanslow.projection.world.listeners.ProjectorListener;

public interface Projector extends RealObject {
	public void addProjectorListener(ProjectorListener l);

	/**
	 * Gets the dimensions of the projector
	 * 
	 * @return Projector dimensions (in mm)
	 */
	public Vector3f getDimensions();

	/**
	 * Gets the ID of the projector
	 * 
	 * @return Unique projector identifier
	 */
	public int getProjectorId();

	/**
	 * Number of rows of pixels
	 * 
	 * @return Vertical resolution (in pixels)
	 */
	public int getResolutionHeight();

	/**
	 * Number of columns of pixels
	 * 
	 * @return Horizontal resolution (in pixels)
	 */
	public int getResolutionWidth();

	/**
	 * Gets the throw ratio for the projector. The ratio of throw:width.
	 * 
	 * @return Throw ratio of projector beam (x:1)
	 */
	public float getThrowRatio();

	/**
	 * Sets the dimensions of the projector
	 * 
	 * @param dimensions
	 *            New dimensions
	 * @throws IllegalArgumentException
	 *             Thrown if dimensions == null
	 */
	void setDimensions(Vector3f dimensions) throws NullPointerException;

	/**
	 * Sets the resolution of the projector
	 * 
	 * @param height
	 *            Number of rows of pixels (must be positive integer)
	 * @param width
	 *            Number of columns of pixels (must be positive integer)
	 * @throws IllegalArgumentException
	 *             Thrown if the height or width is <= 0
	 */
	public void setResolution(int height, int width) throws IllegalArgumentException;

	/**
	 * Sets the throw ratio of the projector lens. The ratio of throw:width.
	 * 
	 * @param throwRatio
	 *            Throw ratio of projector beam (x:1) (positive real number)
	 * @throws IllegalArgumentException
	 *             Thrown if the throwRatio is <= 0
	 */
	public void setThrowRatio(float throwRatio) throws IllegalArgumentException;
}
