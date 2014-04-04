package com.jayanslow.projection.world.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.RealObjectType;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.utils.serializer.AbstractSerializer;
import com.jayanslow.utils.serializer.SerializerFactory;

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
		default:
			throw new RuntimeException("Unhandled RealObjectType in RealObjectSerializer.serialize");
		}
	}

}
