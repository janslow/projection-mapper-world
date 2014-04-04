package com.jayanslow.projection.world.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.Rotation3f;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class Rotation3fSerializer extends AbstractSerializer<Rotation3f> {

	public static final String	KEY_X	= "x";
	public static final String	KEY_Y	= "y";
	public static final String	KEY_Z	= "z";

	public Rotation3fSerializer(final SerializerFactory factory) {
		super(factory, Rotation3f.class);
	}

	@Override
	public Rotation3f deserialize(final JSONObject o) throws JSONException {
		final float x = (float) o.getDouble(KEY_X);
		final float y = (float) o.getDouble(KEY_Y);
		final float z = (float) o.getDouble(KEY_Z);

		return new Rotation3f(x, y, z);
	}

	@Override
	public void serialize(final Rotation3f t, final JSONObject o) throws JSONException {
		o.put(KEY_X, t.x);
		o.put(KEY_Y, t.y);
		o.put(KEY_Z, t.z);
	}
}
