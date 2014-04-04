package com.jayanslow.projection.world.controllers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONObject;

import com.jayanslow.projection.world.json.CuboidScreenSerializer;
import com.jayanslow.projection.world.json.FlatScreenSerializer;
import com.jayanslow.projection.world.json.ProjectorSerializer;
import com.jayanslow.projection.world.json.RealObjectSerializer;
import com.jayanslow.projection.world.json.Rotation3fSerializer;
import com.jayanslow.projection.world.json.ScreenSerializer;
import com.jayanslow.projection.world.json.UniverseSerializer;
import com.jayanslow.projection.world.json.Vector2fSerializer;
import com.jayanslow.projection.world.json.Vector3fSerializer;
import com.jayanslow.projection.world.models.Universe;
import com.jayanslow.utils.serializer.MapSerializerFactory;
import com.jayanslow.utils.serializer.Serializer;
import com.jayanslow.utils.serializer.SerializerFactory;

public class JsonController {
	private static Charset			DEFAULT_CHARSET	= StandardCharsets.UTF_8;
	private final SerializerFactory	f;
	private final Charset			charset;

	public JsonController() {
		this(DEFAULT_CHARSET);
	}

	public JsonController(Charset charset) {
		this.charset = charset;

		f = new MapSerializerFactory(new HashMap<Class<?>, Serializer<?>>());

		// Vecmath Serializers
		f.addSerializer(new Vector2fSerializer(f));
		f.addSerializer(new Vector3fSerializer(f));
		f.addSerializer(new Rotation3fSerializer(f));

		f.addSerializer(new RealObjectSerializer(f));

		// RealObject serializers
		f.addSerializer(new ProjectorSerializer(f));
		f.addSerializer(new ScreenSerializer(f));
		f.addSerializer(new UniverseSerializer(f));

		// Screen Serializers
		f.addSerializer(new CuboidScreenSerializer(f));
		f.addSerializer(new FlatScreenSerializer(f));

	}

	public Universe deserialize(String json) {
		JSONObject o = new JSONObject(json);
		return f.deserialize(Universe.class, o);
	}

	public Universe readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		String json = charset.decode(ByteBuffer.wrap(encoded)).toString();
		return deserialize(json);
	}

	public String serialize(Universe universe, boolean pretty) {
		JSONObject o = f.serialize(Universe.class, universe);
		return o.toString(pretty ? 4 : 0);
	}

	public void writeToFile(Universe universe, String path, boolean pretty) throws IOException {
		String json = serialize(universe, pretty);
		byte[] encoded = charset.encode(json).array();
		Files.write(Paths.get(path), encoded);
	}
}
