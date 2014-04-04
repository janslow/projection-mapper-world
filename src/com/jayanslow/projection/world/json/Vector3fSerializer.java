package com.jayanslow.projection.world.json;

import javax.vecmath.Vector3f;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class Vector3fSerializer extends AbstractSerializer<Vector3f> {

	public static final String	KEY_X	= "x";
	public static final String	KEY_Y	= "y";
	public static final String	KEY_Z	= "z";

	public Vector3fSerializer(final SerializerFactory factory) {
		super(factory, Vector3f.class);
	}

	@Override
	public Vector3f deserialize(final JSONObject o) throws JSONException {
		final float x = (float) o.getDouble(KEY_X);
		final float y = (float) o.getDouble(KEY_Y);
		final float z = (float) o.getDouble(KEY_Z);

		return new Vector3f(x, y, z);
	}

	@Override
	public void serialize(final Vector3f t, final JSONObject o) throws JSONException {
		o.put(KEY_X, t.x);
		o.put(KEY_Y, t.y);
		o.put(KEY_Z, t.z);
	}
}
