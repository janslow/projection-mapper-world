package com.jayanslow.projection.world.json;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.StandardProjector;

public class ProjectorSerializer extends AbstractRealObjectSerializer<Projector> {

	public static final String	KEY_DIMENSIONS			= "dimensions";
	public static final String	KEY_PROJECTOR_ID		= "projector_id";
	public static final String	KEY_RESOLUTION_HEIGHT	= "resolution_height";
	public static final String	KEY_RESOLUTION_WIDTH	= "resolution_width";
	public static final String	KEY_THROW_RATIO			= "throw_ratio";

	public ProjectorSerializer(final SerializerFactory factory) {
		super(factory, Projector.class);
	}

	@Override
	protected Projector deserializeObject(final JSONObject o, final int id, final Vector3f position,
			final AxisAngle4f direction) {
		final int projectorId = o.getInt(KEY_PROJECTOR_ID);

		final Vector3f dimensions = getFactory().deserialize(Vector3f.class, o.getJSONObject(KEY_DIMENSIONS));

		final int height = o.getInt(KEY_RESOLUTION_HEIGHT);
		final int width = o.getInt(KEY_RESOLUTION_WIDTH);

		final float ratio = (float) o.getDouble(KEY_THROW_RATIO);

		return new StandardProjector(id, projectorId, position, direction, dimensions, height, width, ratio);
	}

	@Override
	protected void serializeObject(final Projector t, final JSONObject o) throws JSONException {
		o.put(KEY_PROJECTOR_ID, t.getProjectorId());

		o.put(KEY_DIMENSIONS, getFactory().serialize(Vector3f.class, t.getDimensions()));

		o.put(KEY_RESOLUTION_HEIGHT, t.getResolutionHeight());
		o.put(KEY_RESOLUTION_WIDTH, t.getResolutionWidth());

		o.put(KEY_THROW_RATIO, t.getThrowRatio());
	}

}
