package com.jayanslow.projection.world.models;

import java.util.Collection;
import java.util.Collections;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class FlatScreen extends AbstractRealObject implements Screen {

	private Vector2f	dimensions;
	private final Face	face;
	private final int	screenId;

	public FlatScreen(final int id, final int screenId, final Vector3f position, final AxisAngle4f direction,
			final Vector2f dimensions) {
		super(RealObjectType.SCREEN, id, position, direction);
		this.dimensions = dimensions;
		this.screenId = screenId;

		face = new RectangularFace(0, new Vector3f(0, 0, 0), getDirection(), this.dimensions);
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

	/**
	 * Gets the dimensions of the object
	 * 
	 * @return Dimension vector (in mm)
	 */
	public Vector2f getDimensions() {
		return dimensions;
	}

	public void setDimensions(Vector2f dimensions) throws NullPointerException {
		if (dimensions == null)
			throw new NullPointerException("FlatScreen.dimensions can not be null");
		this.dimensions = dimensions;
	}
}
