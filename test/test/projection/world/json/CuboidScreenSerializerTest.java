package test.projection.world.json;

import static com.jayanslow.utils.json.JsonJunitUtils.assertJsonEquals;
import static org.mockito.Mockito.mock;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.CuboidScreen;
import com.jayanslow.projection.world.json.CuboidScreenSerializer;
import com.jayanslow.projection.world.json.SerializerFactory;

public class CuboidScreenSerializerTest extends AbstractScreenSerializerTest {

	@Test
	public void testDeserialize() {
		final JSONObject json = new JSONObject();
		final SerializerFactory f = mock(SerializerFactory.class);

		prepareDeserializeScreen(f, json);

		final CuboidScreenSerializer s = new CuboidScreenSerializer(f);

		final CuboidScreen actual = s.deserialize(json);

		testDeserializeScreen(actual);
	}

	@Test
	public void testSerialize() {
		final JSONObject expected = new JSONObject();

		final Vector3f position = mock(Vector3f.class);
		final AxisAngle4f direction = mock(AxisAngle4f.class);
		final Vector3f dimensions = mock(Vector3f.class);
		final CuboidScreen t = new CuboidScreen(1, 2, position, direction, dimensions);

		final SerializerFactory f = mock(SerializerFactory.class);
		final CuboidScreenSerializer s = new CuboidScreenSerializer(f);

		prepareSerializeScreen(t, expected, f);

		final JSONObject actual = new JSONObject();
		s.serialize(t, actual);

		assertJsonEquals(expected, actual);
	}

}