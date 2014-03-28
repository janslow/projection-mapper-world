package com.jayanslow.utils.json;

import static junit.framework.Assert.failNotEquals;
import static junit.framework.Assert.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonJunitUtils {
	public static void assertJsonEquals(final Object expected, final Object actual) throws JSONException {
		assertJsonEquals(null, expected, actual);
	}

	private static void assertJsonEquals(final String message, final JSONArray expected, final JSONArray actual)
			throws JSONException {
		if (expected.length() != actual.length()) {
			failLengthNotEquals(message, expected.length(), actual.length());
		}
		for (int i = 0; i < expected.length(); i++) {
			assertJsonEquals(message, expected.get(i), actual.get(i));
		}
	}

	private static void assertJsonEquals(final String message, final JSONObject expected, final JSONObject actual)
			throws JSONException {
		if (expected.length() != actual.length()) {
			failLengthNotEquals(message, expected.length(), actual.length());
		}
		@SuppressWarnings("rawtypes")
		final Iterator iterator = expected.keys();

		while (iterator.hasNext()) {
			// => expected[key] is mapped
			final String key = String.valueOf(iterator.next());
			if (!actual.has(key)) {
				// actual[key] is unmapped
				failPairNotEquals(message, key, expected.get(key), null, null);
			} else if (!expected.isNull(key) && actual.isNull(key)) {
				// actual[key] is NULL but expected[key] is none-null
				failPairNotEquals(message, key, expected.get(key), key, null);
			} else if (expected.isNull(key)) {
				// expected[key] is NULL value (and actual[key] is non-NULL value)
				failPairNotEquals(message, key, null, key, expected.get(key));
			}
			// => expected[key] and actual[key] are both non-NULL values
			assertJsonEquals(message, expected.get(key), actual.get(key));
		}
	}

	public static void assertJsonEquals(final String message, final Object expected, final Object actual)
			throws JSONException {
		if (expected == actual)
			// Equal if same
			return;
		else if (expected == null || actual == null || !expected.getClass().equals(actual.getClass())) {
			// Not equal if one is null or different classes
			failNotEquals(message, expected, actual);
		}

		if (expected.getClass() == JSONObject.class) {
			// Use assertJsonEquals for JSONObjects
			assertJsonEquals(message, (JSONObject) expected, (JSONObject) actual);
		} else if (expected.getClass() == JSONArray.class) {
			// Use assertJsonEquals for JSONArrays
			assertJsonEquals(message, (JSONArray) expected, (JSONArray) actual);
		} else {
			// Otherwise, use standard assertEquals
			assertEquals(message, expected, actual);
		}

	}

	public static void failLengthNotEquals(final String message, final int expectedLength, final int actualLength) {
		final String formatted = message != null ? message + " " : "";
		fail(String.format("%sexpected length %d but was %d", formatted, expectedLength, actualLength));
	}

	public static void failPairNotEquals(final String message, final String expectedKey, final Object expectedValue,
			final String actualKey, final Object actualValue) {
		final String formatString = "%s => %s";
		final String expected = String.format(formatString, expectedKey, expectedValue), actual = String.format(
				formatString, actualKey, actualValue);
		fail(format(message, expected, actual));
	}
}
