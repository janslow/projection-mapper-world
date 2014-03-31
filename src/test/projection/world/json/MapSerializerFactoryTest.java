package test.projection.world.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.MapSerializerFactory;
import com.jayanslow.projection.world.json.Serializer;

public class MapSerializerFactoryTest {

	@SuppressWarnings("unchecked")
	private Serializer<Integer> mockIntegerSerializer() {
		return mock(Serializer.class);
	}

	@Test
	public void testAddSerializer() {
		final Serializer<Integer> mockedSerializer = mockIntegerSerializer();
		when(mockedSerializer.getTargetClass()).thenReturn(Integer.class);

		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		final MapSerializerFactory f = new MapSerializerFactory(map);

		f.addSerializer(mockedSerializer);

		assertTrue(map.containsKey(Integer.class));
		assertSame(mockedSerializer, map.get(Integer.class));
		assertEquals(1, map.size());
	}

	@Test
	public void testContainsSerializer() {
		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		map.put(Integer.class, mock(Serializer.class));
		final MapSerializerFactory f = new MapSerializerFactory(map);

		assertTrue(f.containsSerializer(Integer.class));
	}

	@Test
	public void testDeserialize() {
		final JSONObject json = new JSONObject();
		final Integer expected = 3;

		final Serializer<Integer> mockedSerializer = mockIntegerSerializer();
		when(mockedSerializer.deserialize(json)).thenReturn(expected);

		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		map.put(Integer.class, mockedSerializer);
		final MapSerializerFactory f = new MapSerializerFactory(map);

		final Integer actual = f.deserialize(Integer.class, json);

		verify(mockedSerializer).deserialize(json);
		assertSame(expected, actual);
	}

	@Test
	public void testDeserializeArray() {
		final JSONObject json = new JSONObject();
		final JSONArray array = new JSONArray();
		array.put(json);

		final Integer expected = 3;

		final Serializer<Integer> mockedSerializer = mockIntegerSerializer();
		when(mockedSerializer.deserialize(json)).thenReturn(expected);

		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		map.put(Integer.class, mockedSerializer);
		final MapSerializerFactory f = new MapSerializerFactory(map);

		final List<Integer> actual = f.deserialize(Integer.class, array);

		assertEquals(1, actual.size());

		verify(mockedSerializer).deserialize(json);
		assertSame(expected, actual.get(0));
	}

	@Test
	public void testGetSerializer() {
		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		final Serializer<Integer> mockedSerializer = mockIntegerSerializer();
		map.put(Integer.class, mockedSerializer);
		final MapSerializerFactory f = new MapSerializerFactory(map);

		assertSame(mockedSerializer, f.getSerializer(Integer.class));
	}

	@Test
	public void testSerializeClassOfTCollectionOfT() {
		final Integer t = 3;
		final List<Integer> ts = Collections.singletonList(t);

		final Serializer<Integer> mockedSerializer = mockIntegerSerializer();

		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		map.put(Integer.class, mockedSerializer);
		final MapSerializerFactory f = new MapSerializerFactory(map);

		final JSONArray array = f.serialize(Integer.class, ts);

		assertEquals(1, array.length());

		verify(mockedSerializer).serialize(t, array.getJSONObject(0));
	}

	@Test
	public void testSerializeClassOfTT() {
		final Integer t = 3;

		final Serializer<Integer> mockedSerializer = mockIntegerSerializer();

		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		map.put(Integer.class, mockedSerializer);
		final MapSerializerFactory f = new MapSerializerFactory(map);

		final JSONObject serialized = f.serialize(Integer.class, t);

		verify(mockedSerializer).serialize(t, serialized);
	}

	@Test
	public void testSerializeClassOfTTJSONObject() {
		final Integer t = 3;
		final JSONObject o = new JSONObject();

		final Serializer<Integer> mockedSerializer = mockIntegerSerializer();

		final Map<Class<?>, Serializer<?>> map = new HashMap<>();
		map.put(Integer.class, mockedSerializer);
		final MapSerializerFactory f = new MapSerializerFactory(map);

		f.serialize(Integer.class, t, o);

		verify(mockedSerializer).serialize(t, o);
	}

}
