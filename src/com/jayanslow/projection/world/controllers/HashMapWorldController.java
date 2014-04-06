package com.jayanslow.projection.world.controllers;

import java.util.Collection;
import java.util.HashMap;

import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.projection.world.models.Universe;

public class HashMapWorldController implements WorldController {

	private final Universe						universe;
	private final HashMap<Integer, Projector>	projectors;
	private final HashMap<Integer, Screen>		screens;

	public HashMapWorldController(Universe universe) {
		this.universe = universe;
		projectors = new HashMap<Integer, Projector>();
		screens = new HashMap<Integer, Screen>();
		refreshWorld();
	}

	@Override
	public Projector getProjector(int id) {
		return projectors.get(id);
	}

	@Override
	public Collection<Projector> getProjectors() {
		return projectors.values();
	}

	@Override
	public Screen getScreen(int id) {
		return screens.get(id);
	}

	@Override
	public Collection<Screen> getScreens() {
		return screens.values();
	}

	@Override
	public Universe getUniverse() {
		return universe;
	}

	@Override
	public void refreshWorld() {
		projectors.clear();
		screens.clear();
		for (RealObject o : universe.getChildren())
			switch (o.getType()) {
			case PROJECTOR:
				projectors.put(o.getId(), (Projector) o);
				break;
			case SCREEN:
				screens.put(o.getId(), (Screen) o);
				break;
			default:
				break;

			}
	}
}
