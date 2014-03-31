package com.jayanslow.projection.world.json;

import javax.vecmath.Vector2f;

import org.json.JSONException;
import org.json.JSONObject;

public class Vector2fSerializer extends AbstractSerializer<Vector2f> {

	public static final String	KEY_X	= "x";
	public static final String	KEY_Y	= "y";

	public Vector2fSerializer(final SerializerFactory factory) {
		super(factory, Vector2f.class);
	}

	@Override
	public Vector2f deserialize(final JSONObject o) throws JSONException {
		final float x = (float) o.getDouble(KEY_X);
		final float y = (float) o.getDouble(KEY_Y);

		return new Vector2f(x, y);
	}

	@Override
	public void serialize(final Vector2f t, final JSONObject o) throws JSONException {
		o.put(KEY_X, t.x);
		o.put(KEY_Y, t.y);
	}
}
