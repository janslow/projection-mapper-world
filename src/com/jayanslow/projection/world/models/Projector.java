package com.jayanslow.projection.world.models;


public interface Projector extends RealObject {
	/**
	 * 
	 */

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
	 * The ratio of image width to throw distance for the projector. width = throw * ratio
	 * 
	 * @return Throw ratio of projector beam (1:x)
	 */
	public float getThrowRatio();
}
