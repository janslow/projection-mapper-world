package com.jayanslow.projection.world.json;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;

import com.jayanslow.projection.world.CuboidScreen;

public class CuboidScreenSerializer extends AbstractScreenSerializer<CuboidScreen> {

	public static final String	KEY_DIMENSIONS	= "dimensions";

	public CuboidScreenSerializer(final SerializerFactory factory) {
		super(factory, CuboidScreen.class);
	}

	@Override
	protected CuboidScreen deserializeScreen(final JSONObject o, final int id, final int screenId,
			final Vector3f position, final AxisAngle4f direction) {
		final Vector3f dimensions = getFactory().deserialize(Vector3f.class, o.getJSONObject(KEY_DIMENSIONS));

		return new CuboidScreen(id, screenId, position, direction, dimensions);
	}

	@Override
	protected void serializeScreen(final CuboidScreen t, final JSONObject o) {
		o.put(KEY_DIMENSIONS, getFactory().serialize(Vector3f.class, t.getDimensions()));
	}

}
