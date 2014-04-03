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
	private int	expectedScreenId;

	public AbstractScreenSerializerTest() {
		super();
	}

	protected void prepareDeserializeScreen(final SerializerFactory f, final JSONObject json) {
		prepareDeserializeRealObject(f, json);

		// Prepare Direction and ScreenId
		expectedScreenId = 4;
		json.put("screen_id", expectedScreenId);
	}

	protected void prepareSerializeScreen(final Screen screen, final JSONObject expected, final SerializerFactory f) {
		prepareSerializeRealObject(screen, expected, f);

		expected.put("screen_id", screen.getScreenId());
		expected.put("screen_type", screen.getScreenType().toString().toLowerCase());
	}

	protected void testDeserializeScreen(final Screen actual) {
		testDeserializeRealObject(actual);

		assertEquals(expectedScreenId, actual.getScreenId());
	}

}
