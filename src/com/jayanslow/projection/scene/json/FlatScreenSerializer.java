package com.jayanslow.projection.scene.json;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;

import com.jayanslow.projection.scene.FlatScreen;

public class FlatScreenSerializer extends AbstractScreenSerializer<FlatScreen> {

	public static final String	KEY_DIMENSIONS	= "dimensions";

	public FlatScreenSerializer(final SerializerFactory factory) {
		super(factory, FlatScreen.class);
	}

	@Override
	protected FlatScreen deserializeScreen(final JSONObject o, final int id, final int screenId,
			final Vector3f position, final AxisAngle4f direction) {
		final Vector3f dimensions = getFactory().deserialize(Vector3f.class, o.getJSONObject(KEY_DIMENSIONS));

		return new FlatScreen(id, screenId, position, direction, dimensions);
	}

	@Override
	protected void serializeScreen(final FlatScreen t, final JSONObject o) {
		o.put(KEY_DIMENSIONS, getFactory().serialize(Vector3f.class, t.getDimensions()));
	}

}
