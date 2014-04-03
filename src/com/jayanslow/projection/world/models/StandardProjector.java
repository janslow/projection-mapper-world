package com.jayanslow.projection.world.models;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public class StandardProjector extends AbstractRealObject implements Projector {

	private int				height;
	private final int		projectorId;
	private float			throwRatio;
	private int				width;
	private final Vector3f	dimensions;

	public StandardProjector(final int id, final int projectorId, final Vector3f position, final AxisAngle4f direction,
			final Vector3f dimensions, final int height, final int width, final float throwRatio) {
		super(RealObjectType.PROJECTOR, id, position, direction);
		this.dimensions = dimensions;
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
	public Vector3f getDimensions() {
		return dimensions;
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
		result = prime * result + height;
		result = prime * result + projectorId;
		result = prime * result + Float.floatToIntBits(throwRatio);
		result = prime * result + width;
		return result;
	}

	@Override
	public void setResolution(int height, int width) throws IllegalArgumentException {
		if (height <= 0)
			throw new IllegalArgumentException("height must be > 0");
		if (width <= 0)
			throw new IllegalArgumentException("width must be > 0");
		this.height = height;
		this.width = width;
	}

	@Override
	public void setThrowRatio(float throwRatio) throws IllegalArgumentException {
		if (throwRatio <= 0)
			throw new IllegalArgumentException("ratio must be > 0");
		this.throwRatio = throwRatio;
	}
}
