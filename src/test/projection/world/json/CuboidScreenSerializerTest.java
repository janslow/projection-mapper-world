package test.projection.world.json;

import static com.jayanslow.utils.junit.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.vecmath.Vector3f;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.CuboidScreenSerializer;
import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.CuboidScreen;
import com.jayanslow.projection.world.models.Rotation3f;

public class CuboidScreenSerializerTest extends AbstractScreenSerializerTest {

	@Test
	public void testDeserialize() {
		final JSONObject json = new JSONObject();
		final SerializerFactory f = mock(SerializerFactory.class);

		prepareDeserializeScreen(f, json);

		// Prepare Dimensions
		Vector3f expectedDimensions = new Vector3f(12, 57, 33);
		JSONObject jsonDimensions = mock(JSONObject.class);
		json.put("dimensions", jsonDimensions);
		when(f.deserialize(Vector3f.class, jsonDimensions)).thenReturn(expectedDimensions);

		final CuboidScreenSerializer s = new CuboidScreenSerializer(f);

		final CuboidScreen actual = s.deserialize(json);

		testDeserializeScreen(actual);

		assertEquals(expectedDimensions, actual.getDimensions());
	}

	@Test
	public void testSerialize() {
		final JSONObject expected = new JSONObject();

		// Construct CuboidScreen
		final Vector3f position = new Vector3f(15, 7, 28);
		final Rotation3f rotation = new Rotation3f(12, 68, 3);
		final Vector3f dimensions = new Vector3f(125, 72, 2128);
		final CuboidScreen t = new CuboidScreen(1, 2, position, rotation, dimensions);

		// Create Serializer and SerializerFactory
		final SerializerFactory f = mock(SerializerFactory.class);
		final CuboidScreenSerializer s = new CuboidScreenSerializer(f);

		prepareSerializeScreen(t, expected, f);

		// Prepare Dimensions
		final JSONObject expectedDimensions = mock(JSONObject.class);
		expected.put("dimensions", expectedDimensions);
		when(f.serialize(Vector3f.class, t.getDimensions())).thenReturn(expectedDimensions);

		final JSONObject actual = new JSONObject();
		s.serialize(t, actual);

		assertJsonEquals(expected, actual);
	}

}
