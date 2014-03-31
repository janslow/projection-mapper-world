package test.projection.world.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;

import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.Screen;

public class AbstractScreenSerializerTest extends AbstractRealObjectSerializerTest {

	private Vector3f	expectedDimensions;
	private AxisAngle4f	expectedDirection;
	private int			expectedScreenId;

	public AbstractScreenSerializerTest() {
		super();
	}

	protected void prepareDeserializeScreen(final SerializerFactory f, final JSONObject json) {
		prepareDeserializeRealObject(f, json);

		expectedDimensions = mock(Vector3f.class);
		expectedDirection = mock(AxisAngle4f.class);
		expectedScreenId = 4;
		final JSONObject jsonDimensions = mock(JSONObject.class), jsonDirection = mock(JSONObject.class);
		json.put("dimensions", jsonDimensions);
		json.put("direction", jsonDirection);
		json.put("screen_id", expectedScreenId);
		when(f.deserialize(Vector3f.class, jsonDimensions)).thenReturn(expectedDimensions);
		when(f.deserialize(AxisAngle4f.class, jsonDirection)).thenReturn(expectedDirection);
	}

	protected void prepareSerializeScreen(final Screen screen, final JSONObject expected, final SerializerFactory f) {
		prepareSerializeRealObject(screen, expected, f);

		expected.put("screen_id", screen.getScreenId());
		expected.put("screen_type", screen.getScreenType().toString().toLowerCase());

		final JSONObject expectedDimensions = mock(JSONObject.class);
		expected.put("dimensions", expectedDimensions);
		when(f.serialize(Vector3f.class, screen.getDimensions())).thenReturn(expectedDimensions);

		final JSONObject expectedDirection = mock(JSONObject.class);
		expected.put("direction", expectedDirection);
		when(f.serialize(AxisAngle4f.class, screen.getDirection())).thenReturn(expectedDirection);
	}

	protected void testDeserializeScreen(final Screen actual) {
		testDeserializeRealObject(actual);

		assertSame(expectedDimensions, actual.getDimensions());
		assertSame(expectedDirection, actual.getDirection());
		assertEquals(expectedScreenId, actual.getScreenId());
	}

}
