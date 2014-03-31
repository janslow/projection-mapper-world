package com.jayanslow.projection.world.models;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public class StandardProjector extends AbstractRealObject implements Projector {

	private final AxisAngle4f	direction;
	private final int			height;
	private final int			projectorId;
	private final float			throwRatio;
	private final int			width;

	public StandardProjector(final int id, final int projectorId, final Vector3f position, final AxisAngle4f direction,
			final int height, final int width, final float throwRatio) {
		super(RealObjectType.PROJECTOR, id, position);
		this.direction = direction;
		this.height = height;
		this.projectorId = projectorId;
		this.throwRatio = throwRatio;
		this.width = width;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StandardProjector other = (StandardProjector) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (height != other.height)
			return false;
		if (projectorId != other.projectorId)
			return false;
		if (Float.floatToIntBits(throwRatio) != Float.floatToIntBits(other.throwRatio))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	@Override
	public AxisAngle4f getDirection() {
		return direction;
	}

	@Override
	public int getProjectorId() {
		return projectorId;
	}

	@Override
	public int getResolutionHeight() {
		return height;
	}

	@Override
	public int getResolutionWidth() {
		return width;
	}

	@Override
	public float getThrowRatio() {
		return throwRatio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (direction == null ? 0 : direction.hashCode());
		result = prime * result + height;
		result = prime * result + projectorId;
		result = prime * result + Float.floatToIntBits(throwRatio);
		result = prime * result + width;
		return result;
	}
}
