package com.jayanslow.projection.scene.json;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.scene.Screen;

public abstract class AbstractScreenSerializer<T extends Screen> extends AbstractRealObjectSerializer<T> {
	public static final String	KEY_DIRECTION	= "direction";
	public static final String	KEY_SCREEN_ID	= "screen_id";
	public static final String	KEY_SCREEN_TYPE	= "screen_type";

	public AbstractScreenSerializer(final SerializerFactory factory, final Class<T> targetClass) {
		super(factory, targetClass);
	}

	@Override
	protected T deserializeObject(final JSONObject o, final int id, final Vector3f position) {
		final int screenId = o.getInt(KEY_SCREEN_ID);
		final AxisAngle4f direction = getFactory().deserialize(AxisAngle4f.class, o.getJSONObject(KEY_DIRECTION));

		return deserializeScreen(o, id, screenId, position, direction);
	}

	protected abstract T deserializeScreen(JSONObject o, int id, int screenId, Vector3f position, AxisAngle4f direction);

	@Override
	protected void serializeObject(final T t, final JSONObject o) throws JSONException {
		o.put(KEY_SCREEN_ID, t.getScreenId());
		o.put(KEY_SCREEN_TYPE, t.getScreenType());
		o.put(KEY_DIRECTION, getFactory().serialize(AxisAngle4f.class, t.getDirection()));

		serializeScreen(t, o);
	}

	protected abstract void serializeScreen(T t, JSONObject o);

}
