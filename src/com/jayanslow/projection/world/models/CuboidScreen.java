package com.jayanslow.projection.world.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class CuboidScreen extends AbstractRealObject implements Screen {

	private final Vector3f		dimensions;
	private final AxisAngle4f	direction;
	private final List<Face>	faces;
	private final int			screenId;

	public CuboidScreen(final int id, final int screenId, final Vector3f position, final AxisAngle4f direction,
			final Vector3f dimensions) {
		super(RealObjectType.SCREEN, id, position);
		this.dimensions = dimensions;
		this.direction = direction;
		this.screenId = screenId;

		final float x = dimensions.x, y = dimensions.y, z = dimensions.z;

		faces = new ArrayList<Face>(6);
		// Front and Back
		faces.add(new RectangularFace(0, new Vector3f(0, 0, 0), new AxisAngle4f(0, 0, -1, 0), new Vector2f(x, y)));
		faces.add(new RectangularFace(1, new Vector3f(0, 0, z), new AxisAngle4f(0, 0, +1, 0), new Vector2f(x, y)));

		// Top and Bottom
		faces.add(new RectangularFace(2, new Vector3f(0, y, 0), new AxisAngle4f(0, +1, 0, 0), new Vector2f(x, z)));
		faces.add(new RectangularFace(3, new Vector3f(0, 0, 0), new AxisAngle4f(0, -1, 0, 0), new Vector2f(x, z)));

		// Left and Right
		faces.add(new RectangularFace(4, new Vector3f(0, 0, 0), new AxisAngle4f(-1, 0, 0, 0), new Vector2f(z, y)));
		faces.add(new RectangularFace(5, new Vector3f(x, 0, 0), new AxisAngle4f(+1, 0, 0, 0), new Vector2f(z, y)));
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
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
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
		result = prime * result + (direction == null ? 0 : direction.hashCode());
		result = prime * result + (faces == null ? 0 : faces.hashCode());
		result = prime * result + screenId;
		return result;
	}

}
