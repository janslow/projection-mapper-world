package com.jayanslow.projection.world.models;

import java.util.Collection;

import javax.vecmath.Vector3f;

public class CuboidUniverse extends AbstractRealObject implements Universe {

	private final Collection<RealObject>	children;
	private final Vector3f					dimensions;
	private final DisplayType				displayType;

	public CuboidUniverse(final int id, final Vector3f position, final Vector3f dimensions,
			final Collection<RealObject> children, final DisplayType displayType) {
		super(RealObjectType.UNIVERSE, id, position);
		this.children = children;
		this.dimensions = dimensions;
		this.displayType = displayType;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
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
		if (displayType != other.displayType)
			return false;
		return true;
	}

	@Override
	public Collection<RealObject> getChildren() {
		return children;
	}

	@Override
	public Vector3f getDimensions() {
		return dimensions;
	}

	@Override
	public DisplayType getDisplayType() {
		return displayType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (children == null ? 0 : children.hashCode());
		result = prime * result + (dimensions == null ? 0 : dimensions.hashCode());
		result = prime * result + (displayType == null ? 0 : displayType.hashCode());
		return result;
	}
}
