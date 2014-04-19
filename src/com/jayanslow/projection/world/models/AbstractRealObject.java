package com.jayanslow.projection.world.models;

import javax.vecmath.Vector3f;

import com.jayanslow.projection.world.listeners.AbstractWorldListenable;
import com.jayanslow.projection.world.listeners.RealObjectListener;

public abstract class AbstractRealObject extends AbstractWorldListenable implements RealObject {

	private final int				id;
	private final Vector3f			position;
	private final RealObjectType	type;
	private final Rotation3f		rotation;

	public AbstractRealObject(final RealObjectType type, final int id, final Vector3f position, Rotation3f rotation) {
		super();
		this.type = type;
		this.id = id;
		this.position = new Vector3f(position);
		this.rotation = new Rotation3f(rotation);
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
		if (rotation == null) {
			if (other.rotation != null)
				return false;
		} else if (!rotation.equals(other.rotation))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	private void fireRealObjectMove(Vector3f old) {
		fireWorldChange();
	}

	private void fireRealObjectRotate(Rotation3f old) {
		fireWorldChange();
	}

	@Override
	public int getId() {
		return id;
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
	public RealObjectType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (position == null ? 0 : position.hashCode());
		result = prime * result + (rotation == null ? 0 : rotation.hashCode());
		result = prime * result + (type == null ? 0 : type.hashCode());
		return result;
	}

	@Override
	public void setPosition(Vector3f position) throws NullPointerException {
		if (position == null)
			throw new NullPointerException("position must not be null");

		Vector3f old = new Vector3f(this.position);

		this.position.set(position);

		fireRealObjectMove(old);
	}

	@Override
	public void setRotation(Rotation3f rotation) throws NullPointerException {
		if (rotation == null)
			throw new NullPointerException("FlatScreen.direction can not be null");

		Rotation3f old = new Rotation3f(this.rotation);

		this.rotation.set(rotation);

		fireRealObjectRotate(old);
	}

}
