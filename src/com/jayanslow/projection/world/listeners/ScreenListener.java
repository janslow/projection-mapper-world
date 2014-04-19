package com.jayanslow.projection.world.listeners;

import com.jayanslow.projection.world.models.Screen;

public interface ScreenListener {
	public void screenChangeDimensions(Screen screen, Object old);
}
