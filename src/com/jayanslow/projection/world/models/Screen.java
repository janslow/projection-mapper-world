package com.jayanslow.projection.world.models;

import java.util.Collection;

public interface Screen extends RealObject {

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
