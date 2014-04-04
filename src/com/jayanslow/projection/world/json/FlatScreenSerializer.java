package com.jayanslow.projection.world.json;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;

import com.jayanslow.projection.world.models.FlatScreen;
import com.jayanslow.projection.world.models.Rotation3f;
import com.jayanslow.utils.serializer.SerializerFactory;

public class FlatScreenSerializer extends AbstractScreenSerializer<FlatScreen> {

	public static final String	KEY_DIMENSIONS	= "dimensions";

	public FlatScreenSerializer(final SerializerFactory factory) {
		super(factory, FlatScreen.class);
	}

	@Override
	protected FlatScreen deserializeScreen(final JSONObject o, final int id, final int screenId,
			final Vector3f position, final Rotation3f rotation) {
		final Vector2f dimensions = getFactory().deserialize(Vector2f.class, o.getJSONObject(KEY_DIMENSIONS));

		return new FlatScreen(id, screenId, position, rotation, dimensions);
	}

	@Override
	protected void serializeScreen(final FlatScreen t, final JSONObject o) {
		o.put(KEY_DIMENSIONS, getFactory().serialize(Vector2f.class, t.getDimensions()));
	}

}
