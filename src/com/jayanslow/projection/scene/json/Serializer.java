package com.jayanslow.projection.scene.json;

import org.json.JSONException;
import org.json.JSONObject;

public interface Serializer<T> {
	/**
	 * Parses an object from JSON
	 * 
	 * @param o
	 *            JSON object to parse
	 * @return Parsed object
	 * @throws JSONException
	 *             Thrown if invalid JSON is found
	 */
	public T deserialize(JSONObject o) throws JSONException;

	public Class<T> getTargetClass();

	/**
	 * Serializes an object into JSON
	 * 
	 * @param t
	 *            Object to serialize
	 * @param o
	 *            JSONObject to populate
	 */
	public void serialize(T t, JSONObject o) throws JSONException;
}
