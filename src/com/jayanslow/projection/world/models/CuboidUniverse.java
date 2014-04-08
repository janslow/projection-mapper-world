package com.jayanslow.projection.world.models;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

public class CuboidUniverse implements Universe {

	private final List<RealObject>	children;
	private final Vector3f			dimensions;

	public CuboidUniverse(final Vector3f dimensions, final List<RealObject> children) {
		this.children = children;
		this.dimensions = new Vector3f(dimensions);
	}

	@Override
	public void add(RealObject o) throws NullPointerException {
		if (o != null)
			throw new NullPointerException();
		children.add(o);
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
	public List<RealObject> getChildren() {
		return new ArrayList<>(children);
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
	public boolean remove(RealObject o) {
		return children.remove(o);
	}

	@Override
	public void setDimensions(Vector3f dimensions) {
		this.dimensions.set(dimensions);
	}
}
