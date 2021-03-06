package com.jayanslow.projection.world.models;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import com.jayanslow.projection.world.listeners.ScreenListener;

public class FlatScreen extends AbstractRealObject implements Screen {

	private final Vector2f				dimensions;
	private final Face					face;
	private final int					screenId;
	private final List<ScreenListener>	listeners	= new LinkedList<ScreenListener>();

	public FlatScreen(int id, int screenId) {
		this(id, screenId, new Vector3f(), new Rotation3f(), new Vector2f());
	}

	public FlatScreen(final int id, final int screenId, final Vector3f position, final Rotation3f rotation,
			final Vector2f dimensions) {
		super(RealObjectType.SCREEN, id, position, rotation);
		this.dimensions = new Vector2f(dimensions);
		this.screenId = screenId;

		face = new RectangularFace(0, new Vector3f(), new Rotation3f(), this.dimensions, this);
	}

	public void addScreenListener(ScreenListener l) {
		if (l == null)
			throw new NullPointerException();
		listeners.add(l);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FlatScreen other = (FlatScreen) obj;
		if (dimensions == null) {
			if (other.dimensions != null)
				return false;
		} else if (!dimensions.equals(other.dimensions))
			return false;
		if (screenId != other.screenId)
			return false;
		return true;
	}

	private void fireScreenResize(Vector2f old) {
		for (ScreenListener l : listeners)
			l.screenChangeDimensions(this, old);
		fireWorldChange();
	}

	/**
	 * Gets the dimensions of the object
	 * 
	 * @return Dimension vector (in mm)
	 */
	public Vector2f getDimensions() {
		return new Vector2f(dimensions);
	}

	@Override
	public Face getFace(final int id) {
		if (id != 0)
			return null;
		return face;
	}

	@Override
	public Collection<Face> getFaces() {
		return Collections.singleton(face);
	}

	@Override
	public int getScreenId() {
		return screenId;
	}

	@Override
	public ScreenType getScreenType() {
		return ScreenType.FLAT;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (dimensions == null ? 0 : dimensions.hashCode());
		result = prime * result + (face == null ? 0 : face.hashCode());
		result = prime * result + screenId;
		return result;
	}

	public void setDimensions(Vector2f dimensions) throws NullPointerException {
		if (dimensions == null)
			throw new NullPointerException("FlatScreen.dimensions can not be null");

		Vector2f old = new Vector2f(this.dimensions);

		this.dimensions.set(dimensions);

		fireScreenResize(old);
	}
}
