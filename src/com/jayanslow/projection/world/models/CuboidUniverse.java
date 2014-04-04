package com.jayanslow.projection.world.models;

import java.util.Collection;

import javax.vecmath.Vector3f;

public class CuboidUniverse implements Universe {

	private final Collection<RealObject>	children;
	private final Vector3f					dimensions;

	public CuboidUniverse(final Vector3f dimensions, final Collection<RealObject> children) {
		this.children = children;
		this.dimensions = new Vector3f(dimensions);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		final CuboidUniverse other = (CuboidUniverse) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (dimensions == null) {
			if (other.dimensions != null)
				return false;
		} else if (!dimensions.equals(other.dimensions))
			return false;
		return true;
	}

	@Override
	public Collection<RealObject> getChildren() {
		return children;
	}

	@Override
	public Vector3f getDimensions() {
		return new Vector3f(dimensions);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + (children == null ? 0 : children.hashCode());
		result = prime * result + (dimensions == null ? 0 : dimensions.hashCode());
		return result;
	}

	@Override
	public void setDimensions(Vector3f dimensions) {
		this.dimensions.set(dimensions);
	}
}
