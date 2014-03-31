package test.projection.world.json;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.ScreenSerializer;
import com.jayanslow.projection.world.json.Serializer;
import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.CuboidScreen;
import com.jayanslow.projection.world.models.FlatScreen;
import com.jayanslow.projection.world.models.Screen;
import com.jayanslow.projection.world.models.ScreenType;

public class ScreenSerializerTest {

	@Test
	public void testDeserializeFlatScreen() {
		final JSONObject json = new JSONObject();
		json.put("screen_type", "flat");
		final FlatScreen expected = mock(FlatScreen.class);

		final SerializerFactory f = mock(SerializerFactory.class);
		when(f.deserialize(FlatScreen.class, json)).thenReturn(expected);

		final Serializer<Screen> s = new ScreenSerializer(f);

		assertSame(expected, s.deserialize(json));
	}

	@Test
	public void testSerializeFlatScreen() {
		final FlatScreen t = mock(FlatScreen.class);
		when(t.getScreenType()).thenReturn(ScreenType.FLAT);

		final JSONObject o = new JSONObject();

		final SerializerFactory f = mock(SerializerFactory.class);
		final Serializer<Screen> s = new ScreenSerializer(f);

		s.serialize(t, o);
		verify(f).serialize(FlatScreen.class, t, o);
	}

	@Test
	public void testDeserializeCuboidScreen() {
		final JSONObject json = new JSONObject();
		json.put("screen_type", "cuboid");
		final CuboidScreen expected = mock(CuboidScreen.class);

		final SerializerFactory f = mock(SerializerFactory.class);
		when(f.deserialize(CuboidScreen.class, json)).thenReturn(expected);

		final Serializer<Screen> s = new ScreenSerializer(f);

		assertSame(expected, s.deserialize(json));
	}

	@Test
	public void testSerializeCuboidScreen() {
		final CuboidScreen t = mock(CuboidScreen.class);
		when(t.getScreenType()).thenReturn(ScreenType.CUBOID);

		final JSONObject o = new JSONObject();

		final SerializerFactory f = mock(SerializerFactory.class);
		final Serializer<Screen> s = new ScreenSerializer(f);

		s.serialize(t, o);
		verify(f).serialize(CuboidScreen.class, t, o);
	}
}
