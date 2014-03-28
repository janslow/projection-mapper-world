package com.jayanslow.projection.scene.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapSerializerFactory implements SerializerFactory {

	private final Map<Class<?>, Serializer<?>>	map;

	public MapSerializerFactory(final Map<Class<?>, Serializer<?>> map) {
		this.map = map;
	}

	@Override
	public <T> void addSerializer(final Serializer<T> serializer) {
		map.put(serializer.getTargetClass(), serializer);
	}

	@Override
	public <T> boolean containsSerializer(final Class<T> type) {
		return map.containsKey(type);
	}

	@Override
	public <T> List<T> deserialize(final Class<T> type, final JSONArray array) {
		final List<T> ts = new ArrayList<>(array.length());
		final Serializer<T> serializer = getSerializer(type);
		for (int i = 0; i < array.length(); i++)
			ts.add(serializer.deserialize(array.getJSONObject(i)));
		return ts;
	}

	@Override
	public <T> T deserialize(final Class<T> type, final JSONObject o) {
		return getSerializer(type).deserialize(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Serializer<T> getSerializer(final Class<T> type) {
		final Serializer<T> t = (Serializer<T>) map.get(type);
		if (t == null)
			throw new RuntimeException("No serializer for type in factory: " + type.getCanonicalName());
		return t;
	}

	@Override
	public <T> JSONArray serialize(final Class<T> type, final Collection<T> ts) {
		final JSONArray array = new JSONArray();
		final Serializer<T> serializer = getSerializer(type);
		for (final T t : ts) {
			final JSONObject o = new JSONObject();
			serializer.serialize(t, o);
			array.put(o);
		}
		return array;
	}

	@Override
	public <T> JSONObject serialize(final Class<T> type, final T t) {
		final JSONObject o = new JSONObject();
		serialize(type, t, o);
		return o;
	}

	@Override
	public <T> void serialize(final Class<T> type, final T t, final JSONObject o) {
		getSerializer(type).serialize(t, o);
	}

}
