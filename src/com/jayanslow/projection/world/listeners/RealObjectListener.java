package com.jayanslow.projection.world.listeners;

import javax.vecmath.Vector3f;

import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.Rotation3f;

public interface RealObjectListener {
	public void realObjectMove(RealObject o, Vector3f old);

	public void realObjectRotate(RealObject o, Rotation3f old);
}
