package com.jayanslow.projection.world.models;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class RectangularFace implements Face {

	private final Vector2f		dimensions;
	private final int			faceId;
	private final Vector3f		position;
	private final Rotation3f	rotation;
	private final Screen		screen;
	private final String		friendlyName;

	public RectangularFace(final int faceId, final Vector3f position, final Rotation3f rotation,
			final Vector2f dimensions, Screen screen) {
		this(faceId, position, rotation, dimensions, screen, null);
	}

	public RectangularFace(final int faceId, final Vector3f position, final Rotation3f rotation,
			final Vector2f dimensions, Screen screen, String friendlyName) {
		this.position = new Vector3f(position);
		this.rotation = new Rotation3f(rotation);
		this.dimensions = new Vector2f(dimensions);
		this.faceId = faceId;
		this.screen = screen;
		this.friendlyName = friendlyName;
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
		if (rotation == null) {
			if (other.rotation != null)
				return false;
		} else if (!rotation.equals(other.position))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (screen == null) {
			if (other.screen != null)
				return false;
		} else if (!screen.equals(other.screen))
			return false;
		return true;
	}

	public Vector2f getDimensions() {
		return new Vector2f(dimensions);
	}

	@Override
	public int getFaceId() {
		return faceId;
	}

	@Override
	public String getName() {
		return friendlyName;
	}

	@Override
	public Vector3f getPosition() {
		return new Vector3f(position);
	}

	@Override
	public Rotation3f getRotation() {
		return new Rotation3f(rotation);
	}

	@Override
	public Screen getScreen() {
		return screen;
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

	public void setDimensions(Vector2f dimensions) {
		this.dimensions.set(dimensions);
	}

}
