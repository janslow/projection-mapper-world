package com.jayanslow.projection.world.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.jayanslow.projection.world.listeners.AbstractWorldListenable;
import com.jayanslow.projection.world.listeners.WorldListener;
import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.projection.world.models.Universe;

public class HashMapWorldController extends AbstractWorldListenable implements WorldController {

	private final Universe						universe;
	private final HashMap<Integer, Projector>	projectors			= new HashMap<Integer, Projector>();
	private final HashMap<Integer, Screen>		screens				= new HashMap<Integer, Screen>();
	private final WorldListener					internalListener	= new WorldListener() {

																		@Override
																		public void worldChanged() {
																			fireWorldChange();
																		}

																	};

	public HashMapWorldController(Universe universe) {
		this.universe = universe;

		universe.addWorldListener(internalListener);

		refreshWorld();
	}

	@Override
	public void add(RealObject o) {
		universe.add(o);

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

		o.addWorldListener(internalListener);
		fireWorldChange();
	}

	@Override
	public List<RealObject> getObjects() {
		return new ArrayList<>(universe.getChildren());
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

	public void refreshWorld() {
		projectors.clear();
		screens.clear();

		for (RealObject o : universe.getChildren()) {
			o.addWorldListener(internalListener);
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

	@Override
	public boolean remove(RealObject object) {
		if (!universe.remove(object))
			return false;

		object.removeWorldListener(internalListener);

		switch (object.getType()) {
		case PROJECTOR:
			if (projectors.remove(object) != null)
				return true;
			break;
		case SCREEN:
			if (screens.remove(object) != null)
				return true;
			break;
		default:
			break;
		}

		fireWorldChange();

		return true;
	}

}
