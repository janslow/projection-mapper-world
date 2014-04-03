package test.projection.world.controller;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import org.junit.Before;
import org.junit.Test;

import com.jayanslow.projection.world.controllers.JsonController;
import com.jayanslow.projection.world.models.CuboidScreen;
import com.jayanslow.projection.world.models.CuboidUniverse;
import com.jayanslow.projection.world.models.FlatScreen;
import com.jayanslow.projection.world.models.RealObject;
import com.jayanslow.projection.world.models.RenderMode;
import com.jayanslow.projection.world.models.StandardProjector;
import com.jayanslow.projection.world.models.Universe;

public class JsonControllerTest {
	public static Universe makeSampleWorld() {
		int id = 0;
		Collection<RealObject> objects = new LinkedList<RealObject>();
		Universe universe = new CuboidUniverse(new Vector3f(50, 50, 50), objects, RenderMode.WIREFRAME);

		int projector = 0;
		objects.add(new StandardProjector(id++, projector++, new Vector3f(0, 40, 0), new AxisAngle4f(0, -40, 50, 0),
				new Vector3f(10, 20, 30), 768, 1024, 3));
		objects.add(new StandardProjector(id++, projector++, new Vector3f(0, 50, 30), new AxisAngle4f(0, -50, 20, 0),
				new Vector3f(10, 20, 30), 768, 1024, 1));

		int screen = 0;
		objects.add(new FlatScreen(id++, screen++, new Vector3f(0, 0, 50), new AxisAngle4f(0, 0, -50, 0), new Vector2f(
				50, 50)));
		objects.add(new CuboidScreen(id++, screen++, new Vector3f(20, 0, 35), new AxisAngle4f(0, 0, -50, 0),
				new Vector3f(10, 10, 10)));

		return universe;
	}

	public String	TEMP_PATH;

	@Before
	public void setUpBeforeClass() throws Exception {
		File temp = File.createTempFile("JsonControllerTest_data_", ".json");
		TEMP_PATH = temp.getAbsolutePath();
		System.out.println(TEMP_PATH);
		temp.deleteOnExit();
	}

	@Test
	public void testDeserializePlain() {
		Universe expected = makeSampleWorld();

		JsonController jsonController = new JsonController();

		String json = jsonController.serialize(expected, false);
		Universe actual = jsonController.deserialize(json);

		assertEquals(expected, actual);
	}

	@Test
	public void testDeserializePretty() {
		Universe expected = makeSampleWorld();

		JsonController jsonController = new JsonController();

		String json = jsonController.serialize(expected, true);
		Universe actual = jsonController.deserialize(json);

		assertEquals(expected, actual);
	}

	@Test
	public void testReadFromFileCustomCharset() throws IOException {
		Universe expected = makeSampleWorld();

		JsonController jsonController = new JsonController(StandardCharsets.UTF_16BE);

		PrintWriter pw = new PrintWriter(TEMP_PATH, "UTF-16BE");
		pw.println(jsonController.serialize(expected, false));
		pw.close();

		Universe actual = jsonController.readFromFile(TEMP_PATH);

		assertEquals(expected, actual);
	}

	@Test
	public void testReadFromFileDefaultCharset() throws IOException {
		Universe expected = makeSampleWorld();

		JsonController jsonController = new JsonController();

		PrintWriter pw = new PrintWriter(TEMP_PATH, "UTF-8");
		pw.println(jsonController.serialize(expected, false));
		pw.close();

		Universe actual = jsonController.readFromFile(TEMP_PATH);

		assertEquals(expected, actual);
	}

	@Test
	public void testWriteToFileCustomCharset() throws IOException {
		Universe expected = makeSampleWorld();

		JsonController jsonController = new JsonController(StandardCharsets.UTF_16BE);
		jsonController.writeToFile(expected, TEMP_PATH, false);

		BufferedReader br = new BufferedReader(Files.newBufferedReader(Paths.get(TEMP_PATH), StandardCharsets.UTF_16BE));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null)
			builder.append(line);
		Universe actual = jsonController.deserialize(builder.toString());
		assertEquals(expected, actual);
	}

	@Test
	public void testWriteToFileStandardCharset() throws IOException {
		Universe expected = makeSampleWorld();

		JsonController jsonController = new JsonController();
		jsonController.writeToFile(expected, TEMP_PATH, false);

		BufferedReader br = new BufferedReader(Files.newBufferedReader(Paths.get(TEMP_PATH), StandardCharsets.UTF_8));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null)
			builder.append(line);
		Universe actual = jsonController.deserialize(builder.toString());
		assertEquals(expected, actual);
	}
}
