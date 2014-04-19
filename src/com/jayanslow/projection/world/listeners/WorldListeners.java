package com.jayanslow.projection.world.listeners;

import java.util.LinkedList;
import java.util.List;

public class WorldListeners {
	public final List<WorldListener>	inner;

	public WorldListeners() {
		this(new LinkedList<WorldListener>());
	}

	public WorldListeners(List<WorldListener> worldListeners) {
		inner = worldListeners;
	}

	public void add(WorldListener l) {
		if (l == null)
			throw new NullPointerException();
		if (!inner.contains(l))
			inner.add(l);
	}

	public void fire() {
		for (WorldListener l : inner)
			l.worldChanged();
	}

	public void remove(WorldListener l) {
		inner.remove(l);
	}
}
