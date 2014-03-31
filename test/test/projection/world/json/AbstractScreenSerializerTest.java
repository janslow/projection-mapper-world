package test.projection.world.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.vecmath.AxisAngle4f;

import org.json.JSONObject;

import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.Screen;

public class AbstractScreenSerializerTest extends AbstractRealObjectSerializerTest {
	private AxisAngle4f	expectedDirection;
	private int			expectedScreenId;

	public AbstractScreenSerializerTest() {
		super();
	}

	protected void prepareDeserializeScreen(final SerializerFactory f, final JSONObject json) {
		prepareDeserializeRealObject(f, json);

		// Prepare Direction and ScreenId
		expectedDirection = mock(AxisAngle4f.class);
		expectedScreenId = 4;
		final JSONObject jsonDirection = mock(JSONObject.class);
		json.put("direction", jsonDirection);
		json.put("screen_id", expectedScreenId);
		when(f.deserialize(AxisAngle4f.class, jsonDirection)).thenReturn(expectedDirection);
	}

	protected void prepareSerializeScreen(final Screen screen, final JSONObject expected, final SerializerFactory f) {
		prepareSerializeRealObject(screen, expected, f);

		expected.put("screen_id", screen.getScreenId());
		expected.put("screen_type", screen.getScreenType().toString().toLowerCase());

		final JSONObject expectedDirection = mock(JSONObject.class);
		expected.put("direction", expectedDirection);
		when(f.serialize(AxisAngle4f.class, screen.getDirection())).thenReturn(expectedDirection);
	}

	protected void testDeserializeScreen(final Screen actual) {
		testDeserializeRealObject(actual);

		assertSame(expectedDirection, actual.getDirection());
		assertEquals(expectedScreenId, actual.getScreenId());
	}

}
