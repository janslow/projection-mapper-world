package com.jayanslow.projection.world.json;

import java.util.Collection;

import javax.vecmath.Vector3f;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.CuboidUniverse;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.RenderMode;
import com.jayanslow.projection.world.models.Universe;

public class UniverseSerializer extends AbstractSerializer<Universe> {
	public static final String	KEY_CHILDREN		= "children";
	public static final String	KEY_DIMENSIONS		= "dimensions";
	public static final String	KEY_RENDER_MODE	= "render_mode";

	public UniverseSerializer(final SerializerFactory factory) {
		super(factory, Universe.class);
	}

	@Override
	public Universe deserialize(JSONObject o) throws JSONException {
		final JSONArray jsonChildren = o.getJSONArray(KEY_CHILDREN);
		final Collection<RealObject> children = getFactory().deserialize(RealObject.class, jsonChildren);

		final String jsonDisplayType = o.getString(KEY_RENDER_MODE).toUpperCase();
		final RenderMode displayType = Enum.valueOf(RenderMode.class, jsonDisplayType);

		final Vector3f dimensions = getFactory().deserialize(Vector3f.class, o.getJSONObject(KEY_DIMENSIONS));

		return new CuboidUniverse(dimensions, children, displayType);
	}

	@Override
	public void serialize(Universe t, JSONObject o) throws JSONException {
		final JSONArray jsonChildren = getFactory().serialize(RealObject.class, t.getChildren());
		o.put(KEY_CHILDREN, jsonChildren);

		final JSONObject jsonDimensions = getFactory().serialize(Vector3f.class, t.getDimensions());
		o.put(KEY_DIMENSIONS, jsonDimensions);

		final String jsonDisplayType = t.getDisplayType().toString().toLowerCase();
		o.put(KEY_RENDER_MODE, jsonDisplayType);
	}

}
