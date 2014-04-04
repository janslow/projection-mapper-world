package com.jayanslow.projection.world.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.CuboidScreen;
import com.jayanslow.projection.world.models.FlatScreen;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.projection.world.models.ScreenType;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class ScreenSerializer extends AbstractSerializer<Screen> {

	public ScreenSerializer(final SerializerFactory factory) {
		super(factory, Screen.class);
	}

	@Override
	public Screen deserialize(JSONObject o) throws JSONException {
		final ScreenType type = Enum.valueOf(ScreenType.class, o.getString(AbstractScreenSerializer.KEY_SCREEN_TYPE)
				.toUpperCase());

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
	public void serialize(Screen t, JSONObject o) throws JSONException {
		switch (t.getScreenType()) {
		case CUBOID:
			getFactory().serialize(CuboidScreen.class, (CuboidScreen) t, o);
			break;
		case FLAT:
			getFactory().serialize(FlatScreen.class, (FlatScreen) t, o);
			break;
		default:
			throw new RuntimeException("Unhandled ScreenType in ScreenSerializer.serialize");
		}
	}

}
