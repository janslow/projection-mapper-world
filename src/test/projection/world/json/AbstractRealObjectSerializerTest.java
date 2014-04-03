package test.projection.world.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;

import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.RealObject;

public class AbstractRealObjectSerializerTest {

	private int			expectedId;
	private Vector3f	expectedPosition;
	private AxisAngle4f	expectedDirection;

	public AbstractRealObjectSerializerTest() {
		super();
	}

	protected void prepareDeserializeRealObject(final SerializerFactory f, final JSONObject json) {
		expectedId = 3;
		json.put("id", expectedId);

		expectedPosition = mock(Vector3f.class);
		final JSONObject jsonPosition = mock(JSONObject.class);
		json.put("position", jsonPosition);
		when(f.deserialize(Vector3f.class, jsonPosition)).thenReturn(expectedPosition);

		expectedDirection = mock(AxisAngle4f.class);
		final JSONObject jsonDirection = mock(JSONObject.class);
		json.put("direction", jsonDirection);
		when(f.deserialize(AxisAngle4f.class, jsonDirection)).thenReturn(expectedDirection);
	}

	protected void prepareSerializeRealObject(final RealObject object, final JSONObject expected,
			final SerializerFactory f) {
		expected.put("id", object.getId());
		expected.put("type", object.getType().toString().toLowerCase());

		final JSONObject expectedPosition = mock(JSONObject.class);
		expected.put("position", expectedPosition);
		when(f.serialize(Vector3f.class, object.getPosition())).thenReturn(expectedPosition);

		final JSONObject expectedDirection = mock(JSONObject.class);
		expected.put("direction", expectedDirection);
		when(f.serialize(AxisAngle4f.class, object.getDirection())).thenReturn(expectedDirection);
	}

	protected void testDeserializeRealObject(final RealObject actual) {
		assertEquals(expectedId, actual.getId());
		assertSame(expectedPosition, actual.getPosition());
		assertSame(expectedDirection, actual.getDirection());
	}

}
