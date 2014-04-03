package test.projection.world.json;

import static com.jayanslow.utils.junit.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.Rotation3fSerializer;
import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.Rotation3f;

public class Rotation3fSerializerTest {

	@Test
	public void testDeserialize() {
		final Rotation3f expected = new Rotation3f(1.0f, -3.0f, -2.0f);

		final JSONObject json = new JSONObject();
		json.put("x", expected.x);
		json.put("y", expected.y);
		json.put("z", expected.z);

		final Rotation3f actual = new Rotation3fSerializer(mock(SerializerFactory.class)).deserialize(json);

		assertEquals(expected, actual);
	}

	@Test
	public void testSerialize() {
		final Rotation3f v = new Rotation3f(1.0f, -3.0f, -2.0f);

		final JSONObject expected = new JSONObject();
		expected.put("x", v.x);
		expected.put("y", v.y);
		expected.put("z", v.z);

		final JSONObject actual = new JSONObject();
		new Rotation3fSerializer(mock(SerializerFactory.class)).serialize(v, actual);

		assertJsonEquals(expected, actual);
	}
}
