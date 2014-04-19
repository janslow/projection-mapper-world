package com.jayanslow.projection.world.listeners;


public interface WorldListenable {

	public void addWorldListener(WorldListener l) throws NullPointerException;

	public void removeWorldListener(WorldListener l);

}
