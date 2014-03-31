package test.projection.world.json;

import static com.jayanslow.utils.json.JsonJunitUtils.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.vecmath.Vector3f;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.jayanslow.projection.world.json.SerializerFactory;
import com.jayanslow.projection.world.json.UniverseSerializer;
import com.jayanslow.projection.world.models.CuboidUniverse;
import com.jayanslow.projection.world.models.DisplayType;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.Universe;

public class UniverseSerializerTest extends AbstractRealObjectSerializerTest {

	@Test
	public void testDeserialize() {
		final JSONObject json = new JSONObject();
		final SerializerFactory f = mock(SerializerFactory.class);

		prepareDeserializeRealObject(f, json);

		final DisplayType expectedDisplayType = DisplayType.HOLLOW;
		json.put("display_type", expectedDisplayType.toString().toLowerCase());

		final Vector3f expectedDimensions = mock(Vector3f.class);
		final JSONObject jsonDimensions = mock(JSONObject.class);
		json.put("dimensions", jsonDimensions);
		when(f.deserialize(Vector3f.class, jsonDimensions)).thenReturn(expectedDimensions);

		@SuppressWarnings("unchecked")
		final List<RealObject> expectedChildren = mock(List.class);
		final JSONArray jsonChildren = mock(JSONArray.class);
		json.put("children", jsonChildren);
		when(f.deserialize(RealObject.class, jsonChildren)).thenReturn(expectedChildren);

		final UniverseSerializer s = new UniverseSerializer(f);

		final Universe actual = s.deserialize(json);

		testDeserializeRealObject(actual);

		assertEquals(expectedDisplayType, actual.getDisplayType());
		assertSame(expectedDimensions, actual.getDimensions());
		assertSame(expectedChildren, actual.getChildren());
	}

	@Test
	public void testSerialize() {
		final JSONObject expected = new JSONObject();

		@SuppressWarnings("unchecked")
		final Universe t = new CuboidUniverse(0, mock(Vector3f.class), mock(Vector3f.class),
				(java.util.Collection<RealObject>) mock(List.class), DisplayType.WIRE);

		final SerializerFactory f = mock(SerializerFactory.class);
		final UniverseSerializer s = new UniverseSerializer(f);

		prepareSerializeRealObject(t, expected, f);

		expected.put("display_type", t.getDisplayType().toString().toLowerCase());

		final JSONObject expectedDimensions = mock(JSONObject.class);
		expected.put("dimensions", expectedDimensions);
		when(f.serialize(Vector3f.class, t.getDimensions())).thenReturn(expectedDimensions);

		final JSONArray expectedChildren = mock(JSONArray.class);
		expected.put("children", expectedChildren);
		when(f.serialize(RealObject.class, t.getChildren())).thenReturn(expectedChildren);

		final JSONObject actual = new JSONObject();
		s.serialize(t, actual);

		assertJsonEquals(expected, actual);
	}

}
