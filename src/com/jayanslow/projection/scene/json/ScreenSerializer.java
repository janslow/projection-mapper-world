package com.jayanslow.projection.scene.json;

import javax.vecmath.Vector3f;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.scene.CuboidScreen;
import com.jayanslow.projection.scene.FlatScreen;
import com.jayanslow.projection.scene.Screen;
import com.jayanslow.projection.scene.ScreenType;

public class ScreenSerializer extends AbstractRealObjectSerializer<Screen> {

	public ScreenSerializer(final SerializerFactory factory) {
		super(factory, Screen.class);
	}

	@Override
	protected Screen deserializeObject(final JSONObject o, final int id, final Vector3f position) {
		final ScreenType type = Enum.valueOf(ScreenType.class, o.getString(AbstractScreenSerializer.KEY_SCREEN_TYPE));

		switch (type) {
		case CUBOID:
			return getFactory().deserialize(CuboidScreen.class, o);
		case FLAT:
			return getFactory().deserialize(FlatScreen.class, o);
		default:
			throw new RuntimeException("Unhandled ScreenType in ScreenSerializer.deserialize");
		}
	}

	@Override
	protected void serializeObject(final Screen t, final JSONObject o) throws JSONException {
		switch (t.getScreenType()) {
		case CUBOID:
			getFactory().serialize(CuboidScreen.class, (CuboidScreen) t, o);
		case FLAT:
			getFactory().serialize(FlatScreen.class, (FlatScreen) t, o);
		default:
			throw new RuntimeException("Unhandled ScreenType in ScreenSerializer.serialize");
		}
	}

}
