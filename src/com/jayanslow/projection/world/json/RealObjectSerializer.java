package com.jayanslow.projection.world.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.Projector;
import com.jayanslow.projection.world.RealObject;
import com.jayanslow.projection.world.RealObjectType;
import com.jayanslow.projection.world.Screen;
import com.jayanslow.projection.world.Universe;

public class RealObjectSerializer extends AbstractSerializer<RealObject> {

	public RealObjectSerializer(final SerializerFactory factory) {
		super(factory, RealObject.class);
	}

	@Override
	public RealObject deserialize(final JSONObject o) throws JSONException {
		final String t = o.getString(AbstractRealObjectSerializer.KEY_TYPE).toUpperCase();

		final RealObjectType type = Enum.valueOf(RealObjectType.class, t);

		switch (type) {
		case PROJECTOR:
			return getFactory().deserialize(Projector.class, o);
		case SCREEN:
			return getFactory().deserialize(Screen.class, o);
		case UNIVERSE:
			return getFactory().deserialize(Universe.class, o);
		default:
			throw new RuntimeException("Unhandled RealObjectType in RealObjectSerializer.deserialize");
		}
	}

	@Override
	public void serialize(final RealObject t, final JSONObject o) throws JSONException {
		switch (t.getType()) {
		case PROJECTOR:
			getFactory().serialize(Projector.class, (Projector) t, o);
			break;
		case SCREEN:
			getFactory().serialize(Screen.class, (Screen) t, o);
			break;
		case UNIVERSE:
			getFactory().serialize(Universe.class, (Universe) t, o);
			break;
		default:
			throw new RuntimeException("Unhandled RealObjectType in RealObjectSerializer.serialize");
		}
	}

}
