package com.jayanslow.projection.world.listeners;

import javax.vecmath.Vector3f;

import com.jayanslow.projection.world.models.Rotation3f;

public interface RealObjectListener {
	public void realObjectMove(WorldListenable o, Vector3f old);

	public void realObjectRotate(WorldListenable o, Rotation3f old);
}
