package com.jayanslow.projection.scene.json;

import javax.vecmath.AxisAngle4f;

import org.json.JSONException;
import org.json.JSONObject;

public class AxisAngle4fSerializer extends AbstractSerializer<AxisAngle4f> {

	public static final String	KEY_ANGLE	= "angle";

	public static final String	KEY_X		= "x";
	public static final String	KEY_Y		= "y";
	public static final String	KEY_Z		= "z";

	public AxisAngle4fSerializer(final SerializerFactory factory) {
		super(factory, AxisAngle4f.class);
	}

	@Override
	public AxisAngle4f deserialize(final JSONObject o) throws JSONException {
		final float x = (float) o.getDouble(KEY_X);
		final float y = (float) o.getDouble(KEY_Y);
		final float z = (float) o.getDouble(KEY_Z);
		final float angle = (float) o.getDouble(KEY_ANGLE);

		return new AxisAngle4f(x, y, z, angle);
	}

	@Override
	public void serialize(final AxisAngle4f t, final JSONObject o) throws JSONException {
		o.put(KEY_X, t.x);
		o.put(KEY_Y, t.y);
		o.put(KEY_Z, t.z);
		o.put(KEY_ANGLE, t.angle);
	}

}
