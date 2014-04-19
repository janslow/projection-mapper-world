package com.jayanslow.projection.world.listeners;

import java.util.LinkedList;

public abstract class AbstractWorldListenable implements WorldListenable {

	private final WorldListeners	worldListeners	= new WorldListeners(new LinkedList<WorldListener>());

	@Override
	public void addWorldListener(WorldListener l) throws NullPointerException {
		worldListeners.add(l);
	}

	protected void fireWorldChange() {
		worldListeners.fire();
	}

	@Override
	public void removeWorldListener(WorldListener l) {
		worldListeners.remove(l);
	}
}
