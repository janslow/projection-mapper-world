package com.jayanslow.projection.world.json;

import javax.vecmath.Vector3f;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.Rotation3f;
import com.jayanslow.projection.world.models.Screen;

public abstract class AbstractScreenSerializer<T extends Screen> extends AbstractRealObjectSerializer<T> {
	public static final String	KEY_SCREEN_ID	= "screen_id";
	public static final String	KEY_SCREEN_TYPE	= "screen_type";

	public AbstractScreenSerializer(final SerializerFactory factory, final Class<T> targetClass) {
		super(factory, targetClass);
	}

	@Override
	protected T deserializeObject(final JSONObject o, final int id, final Vector3f position, final Rotation3f rotation) {
		final int screenId = o.getInt(KEY_SCREEN_ID);

		return deserializeScreen(o, id, screenId, position, rotation);
	}

	protected abstract T deserializeScreen(JSONObject o, int id, int screenId, Vector3f position, Rotation3f rotation);

	@Override
	protected void serializeObject(final T t, final JSONObject o) throws JSONException {
		o.put(KEY_SCREEN_ID, t.getScreenId());
		o.put(KEY_SCREEN_TYPE, t.getScreenType().toString().toLowerCase());

		serializeScreen(t, o);
	}

	protected abstract void serializeScreen(T t, JSONObject o);

}
