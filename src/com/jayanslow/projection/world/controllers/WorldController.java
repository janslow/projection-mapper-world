package com.jayanslow.projection.world.controllers;

import java.util.Collection;

import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.projection.world.models.Universe;

public interface WorldController {
	/**
	 * Gets a projector in the world with the specified ID
	 * 
	 * @param id
	 *            Specified ID
	 * @return Projector with the specified ID, or null if there is no such projector
	 */
	public Projector getProjector(int id);

	/**
	 * Gets all projectors in the world
	 * 
	 * @return Collection of projectors
	 */
	public Collection<Projector> getProjectors();

	/**
	 * Gets a screen in the world with the specified ID
	 * 
	 * @param id
	 *            Specified ID
	 * @return Screen with specified ID, or null if there is no such screen
	 */
	public Screen getScreen(int id);

	/**
	 * Gets all screens in the world
	 * 
	 * @return Collection of screens
	 */
	public Collection<Screen> getScreens();

	/**
	 * Gets the universe of the world
	 * 
	 * @return Universe
	 */
	public Universe getUniverse();

	/**
	 * Refreshes cache of projectors and screens
	 */
	public void refreshWorld();
}
