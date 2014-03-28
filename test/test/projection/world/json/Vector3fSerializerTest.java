package test.projection.world.json;

import static com.jayanslow.utils.json.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.vecmath.Vector3f;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.json.Vector3fSerializer;

public class Vector3fSerializerTest {

	@Test
	public void testDeserialize() {
		final Vector3f expected = new Vector3f(1.0f, -3.0f, -2.0f);

		final JSONObject json = new JSONObject();
		json.put("x", expected.x);
		json.put("y", expected.y);
		json.put("z", expected.z);

		final Vector3f actual = new Vector3fSerializer(mock(SerializerFactory.class)).deserialize(json);

		assertEquals(expected, actual);
	}

	@Test
	public void testSerialize() {
		final Vector3f v = new Vector3f(1.0f, -3.0f, -2.0f);

		final JSONObject expected = new JSONObject();
		expected.put("x", v.x);
		expected.put("y", v.y);
		expected.put("z", v.z);

		final JSONObject actual = new JSONObject();
		new Vector3fSerializer(mock(SerializerFactory.class)).serialize(v, actual);

		assertJsonEquals(expected, actual);
	}
}
