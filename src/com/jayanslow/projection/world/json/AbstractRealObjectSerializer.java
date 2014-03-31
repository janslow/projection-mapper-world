package com.jayanslow.projection.world.json;

import javax.vecmath.Vector3f;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.RealObject;

public abstract class AbstractRealObjectSerializer<T extends RealObject> extends AbstractSerializer<T> {
	public static final String	KEY_ID			= "id";
	public static final String	KEY_POSITION	= "position";
	public static final String	KEY_TYPE		= "type";

	public AbstractRealObjectSerializer(final SerializerFactory factory, final Class<T> targetClass) {
		super(factory, targetClass);
	}

	@Override
	public T deserialize(final JSONObject o) throws JSONException {
		final int id = o.getInt(KEY_ID);
		final Vector3f position = getFactory().deserialize(Vector3f.class, o.getJSONObject(KEY_POSITION));

		return deserializeObject(o, id, position);
	}

	protected abstract T deserializeObject(JSONObject o, int id, Vector3f position);

	@Override
	public void serialize(final T t, final JSONObject o) throws JSONException {
		o.put(KEY_ID, t.getId());
		o.put(KEY_POSITION, getFactory().serialize(Vector3f.class, t.getPosition()));
		o.put(KEY_TYPE, t.getType().toString().toLowerCase());

		serializeObject(t, o);
	}

	protected abstract void serializeObject(T t, final JSONObject o) throws JSONException;
}
