package com.jayanslow.projection.world.models;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Vector3f;

import com.jayanslow.projection.world.listeners.ProjectorListener;

public class StandardProjector extends AbstractRealObject implements Projector {

	private int								height;
	private final int						projectorId;
	private float							throwRatio;
	private int								width;
	private final Vector3f					dimensions;
	private final List<ProjectorListener>	listeners	= new LinkedList<>();

	public StandardProjector(int id, int projectorId) {
		this(id, projectorId, new Vector3f(), new Rotation3f(), new Vector3f(), 1, 1, 1);
	}

	public StandardProjector(final int id, final int projectorId, final Vector3f position, final Rotation3f rotation,
			final Vector3f dimensions, final int height, final int width, final float throwRatio) {
		super(RealObjectType.PROJECTOR, id, position, rotation);
		this.dimensions = new Vector3f(dimensions);
		this.height = height;
		this.projectorId = projectorId;
		this.throwRatio = throwRatio;
		this.width = width;
	}

	@Override
	public void addProjectorListener(ProjectorListener l) {
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

	private void fireProjectorChangeResolution(int oldHeight, int oldWidth) {
		for (ProjectorListener l : listeners)
			l.projectorChangeResolution(this, oldHeight, oldWidth);
		fireWorldChange();
	}

	private void fireProjectorResize(Vector3f old) {
		for (ProjectorListener l : listeners)
			l.projectorResize(this, old);
		fireWorldChange();
	}

	private void fireProjectorThrowRatioChange(float old) {
		for (ProjectorListener l : listeners)
			l.projectorChangeThrowRatio(this, old);
		fireWorldChange();
	}

	@Override
	public Vector3f getDimensions() {
		return new Vector3f(dimensions);
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
	public void setDimensions(Vector3f dimensions) throws IllegalArgumentException {
		Vector3f old = new Vector3f(this.dimensions);
		this.dimensions.set(dimensions);
		fireProjectorResize(old);
	}

	@Override
	public void setResolution(int height, int width) throws IllegalArgumentException {
		if (height <= 0)
			throw new IllegalArgumentException("height must be > 0");
		if (width <= 0)
			throw new IllegalArgumentException("width must be > 0");

		int oldHeight = this.height, oldWidth = this.width;

		this.height = height;
		this.width = width;

		fireProjectorChangeResolution(oldHeight, oldWidth);
	}

	@Override
	public void setThrowRatio(float throwRatio) throws IllegalArgumentException {
		if (throwRatio <= 0)
			throw new IllegalArgumentException("ratio must be > 0");

		float old = this.throwRatio;

		this.throwRatio = throwRatio;

		fireProjectorThrowRatioChange(old);
	}
}
