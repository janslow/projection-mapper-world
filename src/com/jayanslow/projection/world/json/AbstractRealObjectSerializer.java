package com.jayanslow.projection.world.json;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.RealObject;

public abstract class AbstractRealObjectSerializer<T extends RealObject> extends AbstractSerializer<T> {
	public static final String	KEY_ID			= "id";
	public static final String	KEY_POSITION	= "position";
	public static final String	KEY_DIRECTION	= "direction";
	public static final String	KEY_TYPE		= "type";

	public AbstractRealObjectSerializer(final SerializerFactory factory, final Class<T> targetClass) {
		super(factory, targetClass);
	}

	@Override
	public T deserialize(final JSONObject o) throws JSONException {
		final int id = o.getInt(KEY_ID);
		final Vector3f position = getFactory().deserialize(Vector3f.class, o.getJSONObject(KEY_POSITION));
		final AxisAngle4f direction = getFactory().deserialize(AxisAngle4f.class, o.getJSONObject(KEY_DIRECTION));

		return deserializeObject(o, id, position, direction);
	}

	protected abstract T deserializeObject(JSONObject o, int id, Vector3f position, AxisAngle4f direction);

	@Override
	public void serialize(final T t, final JSONObject o) throws JSONException {
		o.put(KEY_ID, t.getId());
		o.put(KEY_POSITION, getFactory().serialize(Vector3f.class, t.getPosition()));
		o.put(KEY_DIRECTION, getFactory().serialize(AxisAngle4f.class, t.getDirection()));
		o.put(KEY_TYPE, t.getType().toString().toLowerCase());

		serializeObject(t, o);
	}

	protected abstract void serializeObject(T t, final JSONObject o) throws JSONException;
}
