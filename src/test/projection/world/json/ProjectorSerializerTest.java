package test.projection.world.json;

import static com.jayanslow.utils.junit.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertEquals;
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

		Vector3f expectedDimensions = mock(Vector3f.class);
		final JSONObject jsonDimensions = mock(JSONObject.class);
		json.put("dimensions", jsonDimensions);
		when(f.deserialize(Vector3f.class, jsonDimensions)).thenReturn(expectedDimensions);

		final int expectedProjectorId = 10;
		final int expectedHeight = 11;
		final int expectedWidth = 12;
		final float expectedThrow = 13.0f;
		json.put("projector_id", expectedProjectorId);
		json.put("resolution_height", expectedHeight);
		json.put("resolution_width", expectedWidth);
		json.put("throw_ratio", expectedThrow);

		final ProjectorSerializer s = new ProjectorSerializer(f);

		final Projector actual = s.deserialize(json);

		testDeserializeRealObject(actual);

		assertEquals(expectedProjectorId, actual.getProjectorId());
		assertEquals(expectedDimensions, actual.getDimensions());
		assertEquals(expectedHeight, actual.getResolutionHeight());
		assertEquals(expectedWidth, actual.getResolutionWidth());
		assertEquals(expectedThrow, actual.getThrowRatio(), 0);
	}

	@Test
	public void testSerialize() {
		final JSONObject expected = new JSONObject();

		final Projector t = new StandardProjector(0, 1, mock(Vector3f.class), mock(AxisAngle4f.class),
				mock(Vector3f.class), 2, 3, 4.0f);

		final SerializerFactory f = mock(SerializerFactory.class);
		final ProjectorSerializer s = new ProjectorSerializer(f);

		prepareSerializeRealObject(t, expected, f);

		final JSONObject expectedDimensisons = mock(JSONObject.class);
		expected.put("dimensions", expectedDimensisons);
		when(f.serialize(Vector3f.class, t.getDimensions())).thenReturn(expectedDimensisons);

		expected.put("projector_id", t.getProjectorId());
		expected.put("resolution_height", t.getResolutionHeight());
		expected.put("resolution_width", t.getResolutionWidth());
		expected.put("throw_ratio", t.getThrowRatio());

		final JSONObject actual = new JSONObject();
		s.serialize(t, actual);

		assertJsonEquals(expected, actual);
	}
}
