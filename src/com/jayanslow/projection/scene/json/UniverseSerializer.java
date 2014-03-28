package com.jayanslow.projection.scene.json;

import java.util.Collection;

import javax.vecmath.Vector3f;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.scene.CuboidUniverse;
import com.jayanslow.projection.scene.DisplayType;
import com.jayanslow.projection.scene.RealObject;
import com.jayanslow.projection.scene.Universe;

public class UniverseSerializer extends AbstractRealObjectSerializer<Universe> {
	public static final String	KEY_CHILDREN		= "children";
	public static final String	KEY_DIMENSIONS		= "dimensions";
	public static final String	KEY_DISPLAY_TYPE	= "display_type";

	public UniverseSerializer(final SerializerFactory factory) {
		super(factory, Universe.class);
	}

	@Override
	protected Universe deserializeObject(final JSONObject o, final int id, final Vector3f position) {
		final JSONArray jsonChildren = o.getJSONArray(KEY_CHILDREN);
		final Collection<RealObject> children = getFactory().deserialize(RealObject.class, jsonChildren);

		final String jsonDisplayType = o.getString(KEY_DISPLAY_TYPE);
		final DisplayType displayType = Enum.valueOf(DisplayType.class, jsonDisplayType);

		final Vector3f dimensions = getFactory().deserialize(Vector3f.class, o.getJSONObject(KEY_DIMENSIONS));

		return new CuboidUniverse(id, position, dimensions, children, displayType);
	}

	@Override
	protected void serializeObject(final Universe t, final JSONObject o) throws JSONException {
		final JSONArray jsonChildren = getFactory().serialize(RealObject.class, t.getChildren());
		o.put(KEY_CHILDREN, jsonChildren);

		final JSONObject jsonDimensions = getFactory().serialize(Vector3f.class, t.getDimensions());
		o.put(KEY_DIMENSIONS, jsonDimensions);

		final String jsonDisplayType = t.getDisplayType().toString();
		o.put(KEY_DISPLAY_TYPE, jsonDisplayType);
	}

}
