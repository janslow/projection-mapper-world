package com.jayanslow.projection.world.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class CuboidScreen extends AbstractRealObject implements Screen {

	private final Vector3f		dimensions;
	private final List<Face>	faces;
	private final int			screenId;

	public CuboidScreen(int id, int screenId) {
		this(id, screenId, new Vector3f(), new Rotation3f(), new Vector3f());
	}

	public CuboidScreen(final int id, final int screenId, final Vector3f position, final Rotation3f rotation,
			final Vector3f dimensions) {
		super(RealObjectType.SCREEN, id, position, rotation);
		this.dimensions = new Vector3f(dimensions);
		this.screenId = screenId;

		final float x = dimensions.x, y = dimensions.y, z = dimensions.z;

		faces = new ArrayList<Face>(6);
		// Front and Back
		faces.add(new RectangularFace(0, new Vector3f(x, 0, 0), new Rotation3f(0, (float) Math.PI, 0), new Vector2f(x,
				y), this, "Front"));
		faces.add(new RectangularFace(1, new Vector3f(0, 0, z), new Rotation3f(), new Vector2f(x, y), this, "Back"));

		// Top and Bottom
		faces.add(new RectangularFace(2, new Vector3f(x, y, 0),
				new Rotation3f((float) Math.PI / -2, 0, (float) Math.PI), new Vector2f(x, z), this, "Top"));
		faces.add(new RectangularFace(3, new Vector3f(0, 0, 0), new Rotation3f((float) Math.PI / 2, 0, 0),
				new Vector2f(x, z), this, "Bottom"));

		// Left and Right
		faces.add(new RectangularFace(4, new Vector3f(0, 0, 0), new Rotation3f(0, (float) -Math.PI / 2, 0),
				new Vector2f(z, y), this, "Left"));
		faces.add(new RectangularFace(5, new Vector3f(x, 0, z), new Rotation3f(0, (float) Math.PI / 2, 0),
				new Vector2f(z, y), this, "Right"));
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CuboidScreen other = (CuboidScreen) obj;
		if (dimensions == null) {
			if (other.dimensions != null)
				return false;
		} else if (!dimensions.equals(other.dimensions))
			return false;
		if (screenId != other.screenId)
			return false;
		return true;
	}

	/**
	 * Gets the dimensions of the object
	 * 
	 * @return Dimension vector (in mm)
	 */
	public Vector3f getDimensions() {
		return new Vector3f(dimensions);
	}

	@Override
	public Face getFace(final int id) {
		if (id < 0 || id >= 6)
			return null;
		return faces.get(id);
	}

	@Override
	public Collection<Face> getFaces() {
		return faces;
	}

	@Override
	public int getScreenId() {
		return screenId;
	}

	@Override
	public ScreenType getScreenType() {
		return ScreenType.CUBOID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (dimensions == null ? 0 : dimensions.hashCode());
		result = prime * result + (faces == null ? 0 : faces.hashCode());
		result = prime * result + screenId;
		return result;
	}

	public void setDimensions(Vector3f dimensions) {
		this.dimensions.set(dimensions);
	}

}
