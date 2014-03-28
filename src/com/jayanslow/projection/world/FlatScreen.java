package com.jayanslow.projection.world;

import java.util.Collection;
import java.util.Collections;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public class FlatScreen extends AbstractRealObject implements Screen {

	private final Vector3f		dimensions;
	private final AxisAngle4f	direction;
	private final Face			face;
	private final int			screenId;

	public FlatScreen(final int id, final int screenId, final Vector3f position, final AxisAngle4f direction,
			final Vector3f dimensions) {
		super(RealObjectType.SCREEN, id, position);
		this.direction = direction;
		this.dimensions = dimensions;
		this.screenId = screenId;

		face = new RectangularFace(0, new Vector3f(0, 0, 0), this.dimensions);
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
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (face == null) {
			if (other.face != null)
				return false;
		} else if (!face.equals(other.face))
			return false;
		if (screenId != other.screenId)
			return false;
		return true;
	}

	@Override
	public Vector3f getDimensions() {
		return dimensions;
	}

	@Override
	public AxisAngle4f getDirection() {
		return direction;
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
		result = prime * result + (direction == null ? 0 : direction.hashCode());
		result = prime * result + (face == null ? 0 : face.hashCode());
		result = prime * result + screenId;
		return result;
	}

}
