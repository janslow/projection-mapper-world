package com.jayanslow.projection.world.json;

import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public interface SerializerFactory {
	/**
	 * Adds a serializer to the factory
	 * 
	 * @param type
	 *            Type of object that the serializer can handle
	 * @param serializer
	 *            Serializer to add
	 */
	public <T> void addSerializer(Serializer<T> serializer);

	/**
	 * Checks if the factory can serialize an object class
	 * 
	 * @param type
	 *            Type to check
	 * @return True if the type can be serialized, otherwise false
	 */
	public <T> boolean containsSerializer(Class<T> type);

	/**
	 * Deserializes a collection of objects from JSON
	 * 
	 * @param type
	 *            Type of objects to return
	 * @param array
	 *            JSON array to deserialize
	 * @return Collection of deserialized objects
	 */
	public <T> List<T> deserialize(Class<T> type, JSONArray array);

	/**
	 * Deserializes a single object from JSON
	 * 
	 * @param type
	 *            Type of object to return
	 * @param o
	 *            JSON to deserialize
	 * @return Deserialized object
	 */
	public <T> T deserialize(Class<T> type, JSONObject o);

	/**
	 * Gets a serializer for the specified type
	 * 
	 * @param type
	 *            Specified type
	 * @return Serializer for specified type or null if no serializer is found
	 */
	public <T> Serializer<T> getSerializer(Class<T> type);

	/**
	 * Serializes a collection of objects into JSON
	 * 
	 * @param type
	 *            Type of objects
	 * @param os
	 *            Objects to serialize
	 * @return JSON array of objects
	 */
	public <T> JSONArray serialize(Class<T> type, Collection<T> os);

	/**
	 * Serializes an object into JSON
	 * 
	 * @param type
	 *            Type of object
	 * @param o
	 *            Object to serialize
	 * @return Serialized objects
	 */
	public <T> JSONObject serialize(Class<T> type, T o);

	/**
	 * Serializes an object into JSON
	 * 
	 * @param type
	 *            Type of object
	 * @param o
	 *            Object to serialize
	 * @param json
	 *            JSON Object to populate
	 */
	public <T> void serialize(Class<T> type, T o, JSONObject json);
}
