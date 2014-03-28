package test.projection.world.json;

import static com.jayanslow.utils.json.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.vecmath.AxisAngle4f;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.AxisAngle4fSerializer;
import com.jayanslow.projection.world.json.SerializerFactory;

public class AxisAngle4fSerializerTest {

	@Test
	public void testDeserialize() {
		final AxisAngle4f expected = new AxisAngle4f(1.0f, -3.0f, -2.0f, -4.0f);

		final JSONObject json = new JSONObject();
		json.put("x", expected.x);
		json.put("y", expected.y);
		json.put("z", expected.z);
		json.put("angle", expected.angle);

		final AxisAngle4f actual = new AxisAngle4fSerializer(mock(SerializerFactory.class)).deserialize(json);

		assertEquals(expected, actual);
	}

	@Test
	public void testSerialize() {
		final AxisAngle4f v = new AxisAngle4f(1.0f, -3.0f, -2.0f, -4.0f);

		final JSONObject expected = new JSONObject();
		expected.put("x", v.x);
		expected.put("y", v.y);
		expected.put("z", v.z);
		expected.put("angle", v.angle);

		final JSONObject actual = new JSONObject();
		new AxisAngle4fSerializer(mock(SerializerFactory.class)).serialize(v, actual);

		assertJsonEquals(expected, actual);
	}
}
