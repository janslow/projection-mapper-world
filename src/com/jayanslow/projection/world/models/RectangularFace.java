package com.jayanslow.projection.world.models;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class RectangularFace implements Face {

	private final Vector2f		dimensions;
	private final int			faceId;
	private final Vector3f		position;
	private final AxisAngle4f	direction;

	public RectangularFace(final int faceId, final Vector3f position, final AxisAngle4f direction,
			final Vector2f dimensions) {
		this.position = position;
		this.direction = direction;
		this.dimensions = dimensions;
		this.faceId = faceId;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RectangularFace other = (RectangularFace) obj;
		if (dimensions == null) {
			if (other.dimensions != null)
				return false;
		} else if (!dimensions.equals(other.dimensions))
			return false;
		if (faceId != other.faceId)
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.position))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	public Vector2f getDimensions() {
		return dimensions;
	}

	public AxisAngle4f getDirection() {
		return direction;
	}

	@Override
	public int getFaceId() {
		return faceId;
	}

	@Override
	public Vector3f getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (dimensions == null ? 0 : dimensions.hashCode());
		result = prime * result + faceId;
		result = prime * result + (position == null ? 0 : position.hashCode());
		return result;
	}

}
