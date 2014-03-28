package com.jayanslow.projection.scene;

import javax.vecmath.Vector3f;

public abstract class AbstractRealObject implements RealObject {

	protected final int				id;
	protected final Vector3f		position;
	private final RealObjectType	type;

	public AbstractRealObject(final RealObjectType type, final int id, final Vector3f position) {
		super();
		this.type = type;
		this.id = id;
		this.position = position;
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
		result = prime * result + (type == null ? 0 : type.hashCode());
		return result;
	}

}
