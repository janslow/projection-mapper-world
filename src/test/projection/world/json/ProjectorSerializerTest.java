package test.projection.world.json;

import static com.jayanslow.utils.junit.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.ProjectorSerializer;
import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.models.Projector;
import com.jayanslow.projection.world.models.StandardProjector;

public class ProjectorSerializerTest extends AbstractRealObjectSerializerTest {

	@Test
	public void testDeserialize() {
		final JSONObject json = new JSONObject();
		final SerializerFactory f = mock(SerializerFactory.class);

		prepareDeserializeRealObject(f, json);

		final int expectedProjectorId = 10;
		final int expectedHeight = 11;
		final int expectedWidth = 12;
		final float expectedThrow = 13.0f;
		json.put("projector_id", expectedProjectorId);
		json.put("resolution_height", expectedHeight);
		json.put("resolution_width", expectedWidth);
		json.put("throw_ratio", expectedThrow);

		final AxisAngle4f expectedDirection = mock(AxisAngle4f.class);
		final JSONObject jsonDirection = mock(JSONObject.class);
		json.put("direction", jsonDirection);
		when(f.deserialize(AxisAngle4f.class, jsonDirection)).thenReturn(expectedDirection);

		final ProjectorSerializer s = new ProjectorSerializer(f);

		final Projector actual = s.deserialize(json);

		testDeserializeRealObject(actual);

		assertEquals(expectedProjectorId, actual.getProjectorId());
		assertEquals(expectedHeight, actual.getResolutionHeight());
		assertEquals(expectedWidth, actual.getResolutionWidth());
		assertEquals(expectedThrow, actual.getThrowRatio(), 0);

		assertSame(expectedDirection, actual.getDirection());
	}

	@Test
	public void testSerialize() {
		final JSONObject expected = new JSONObject();

		final Projector t = new StandardProjector(0, 1, mock(Vector3f.class), mock(AxisAngle4f.class), 2, 3, 4.0f);

		final SerializerFactory f = mock(SerializerFactory.class);
		final ProjectorSerializer s = new ProjectorSerializer(f);

		prepareSerializeRealObject(t, expected, f);

		expected.put("projector_id", t.getProjectorId());
		expected.put("resolution_height", t.getResolutionHeight());
		expected.put("resolution_width", t.getResolutionWidth());
		expected.put("throw_ratio", t.getThrowRatio());

		final JSONObject expectedDirection = mock(JSONObject.class);
		expected.put("direction", expectedDirection);
		when(f.serialize(AxisAngle4f.class, t.getDirection())).thenReturn(expectedDirection);

		final JSONObject actual = new JSONObject();
		s.serialize(t, actual);

		assertJsonEquals(expected, actual);
	}

}
