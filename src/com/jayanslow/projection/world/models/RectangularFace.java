package com.jayanslow.projection.world.models;

import javax.vecmath.Vector3f;

public class RectangularFace implements Face {

	private final Vector3f	dimensions;
	private final int		faceId;
	private final Vector3f	position;

	public RectangularFace(final int faceId, final Vector3f position, final Vector3f dimensions) {
		this.position = position;
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
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public Vector3f getDimensions() {
		return dimensions;
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
