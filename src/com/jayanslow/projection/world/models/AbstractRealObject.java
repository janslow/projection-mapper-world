package com.jayanslow.projection.world.models;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public abstract class AbstractRealObject implements RealObject {

	private final int				id;
	private Vector3f				position;
	private final RealObjectType	type;
	private AxisAngle4f				direction;

	public AbstractRealObject(final RealObjectType type, final int id, final Vector3f position, AxisAngle4f direction) {
		super();
		this.type = type;
		this.id = id;
		this.position = position;
		this.direction = direction;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AbstractRealObject other = (AbstractRealObject) obj;
		if (id != other.id)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Vector3f getPosition() {
		return position;
	}

	@Override
	public RealObjectType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (position == null ? 0 : position.hashCode());
		result = prime * result + (direction == null ? 0 : direction.hashCode());
		result = prime * result + (type == null ? 0 : type.hashCode());
		return result;
	}

	@Override
	public void setPosition(Vector3f position) throws NullPointerException {
		if (position == null)
			throw new NullPointerException("position must not be null");
		this.position = position;
	}

	@Override
	public AxisAngle4f getDirection() {
		return direction;
	}

	@Override
	public void setDirection(AxisAngle4f direction) throws NullPointerException {
		if (direction == null)
			throw new NullPointerException("FlatScreen.direction can not be null");
		this.direction = direction;
	}

}
