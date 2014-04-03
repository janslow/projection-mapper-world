package test.projection.world.json;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.RealObjectSerializer;
import com.jayanslow.projection.world.json.Serializer;
import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.RealObjectType;
import com.jayanslow.projection.world.models.Screen;

public class RealObjectSerializerTest {

	@Test
	public void testDeserializeScreen() {
		final JSONObject json = new JSONObject();
		json.put("type", "screen");
		final Screen expected = mock(Screen.class);

		final SerializerFactory f = mock(SerializerFactory.class);
		when(f.deserialize(Screen.class, json)).thenReturn(expected);

		final Serializer<RealObject> s = new RealObjectSerializer(f);

		assertSame(expected, s.deserialize(json));
	}

	@Test
	public void testDeserializeProjector() {
		final JSONObject json = new JSONObject();
		json.put("type", "projector");
		final Projector expected = mock(Projector.class);

		final SerializerFactory f = mock(SerializerFactory.class);
		when(f.deserialize(Projector.class, json)).thenReturn(expected);

		final Serializer<RealObject> s = new RealObjectSerializer(f);

		assertSame(expected, s.deserialize(json));
	}

	@Test
	public void testSerializeProjector() {
		final Projector t = mock(Projector.class);
		when(t.getType()).thenReturn(RealObjectType.PROJECTOR);

		final JSONObject o = new JSONObject();

		final SerializerFactory f = mock(SerializerFactory.class);
		final Serializer<RealObject> s = new RealObjectSerializer(f);

		s.serialize(t, o);
		verify(f).serialize(Projector.class, t, o);
	}

	@Test
	public void testSerializeScreen() {
		final Screen t = mock(Screen.class);
		when(t.getType()).thenReturn(RealObjectType.SCREEN);

		final JSONObject o = new JSONObject();

		final SerializerFactory f = mock(SerializerFactory.class);
		final Serializer<RealObject> s = new RealObjectSerializer(f);

		s.serialize(t, o);
		verify(f).serialize(Screen.class, t, o);
	}

}
