package com.jayanslow.projection.world.listeners;

import javax.vecmath.Vector3f;

import com.jayanslow.projection.world.models.Projector;

public interface ProjectorListener extends RealObjectListener {
	public void projectorChangeResolution(Projector p, int oldHeight, int oldWidth);

	public void projectorChangeThrowRatio(Projector p, float old);

	public void projectorResize(Projector p, Vector3f old);
}
