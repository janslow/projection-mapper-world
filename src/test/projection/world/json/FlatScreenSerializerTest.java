package test.projection.world.json;

import static com.jayanslow.utils.junit.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.FlatScreenSerializer;
import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.FlatScreen;

public class FlatScreenSerializerTest extends AbstractScreenSerializerTest {

	@Test
	public void testDeserialize() {
		final JSONObject json = new JSONObject();
		final SerializerFactory f = mock(SerializerFactory.class);

		prepareDeserializeScreen(f, json);

		// Prepare Dimensions
		Vector2f expectedDimensions = mock(Vector2f.class);
		JSONObject jsonDimensions = mock(JSONObject.class);
		json.put("dimensions", jsonDimensions);
		when(f.deserialize(Vector2f.class, jsonDimensions)).thenReturn(expectedDimensions);

		final FlatScreenSerializer s = new FlatScreenSerializer(f);

		final FlatScreen actual = s.deserialize(json);

		testDeserializeScreen(actual);

		assertSame(expectedDimensions, actual.getDimensions2d());
	}

	@Test
	public void testSerialize() {
		final JSONObject expected = new JSONObject();

		final Vector3f position = mock(Vector3f.class);
		final AxisAngle4f direction = mock(AxisAngle4f.class);
		final Vector2f dimensions = mock(Vector2f.class);
		final FlatScreen t = new FlatScreen(1, 2, position, direction, dimensions);

		final SerializerFactory f = mock(SerializerFactory.class);
		final FlatScreenSerializer s = new FlatScreenSerializer(f);

		prepareSerializeScreen(t, expected, f);

		// Prepare Dimensions
		final JSONObject expectedDimensions = mock(JSONObject.class);
		expected.put("dimensions", expectedDimensions);
		when(f.serialize(Vector2f.class, t.getDimensions2d())).thenReturn(expectedDimensions);

		final JSONObject actual = new JSONObject();
		s.serialize(t, actual);

		assertJsonEquals(expected, actual);
	}

}
