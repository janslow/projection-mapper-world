package com.jayanslow.projection.world.controllers;

import java.util.Collection;
import java.util.List;

import com.jayanslow.projection.world.listeners.WorldListenable;
import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.projection.world.models.Universe;

public interface WorldController extends WorldListenable {
	/**
	 * Adds an object to the universe
	 * 
	 * @param object
	 *            Object to add
	 */
	public void add(RealObject object);

	/**
	 * Gets the objects in the universe
	 * 
	 * @return
	 */
	public List<RealObject> getObjects();

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
	 * Removes an object from the world
	 * 
	 * @param object
	 *            Object to remove
	 * @return True if object is removed, otherwise false
	 */
	public boolean remove(RealObject object);
}
