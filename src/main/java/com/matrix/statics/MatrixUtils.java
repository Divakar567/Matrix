package com.matrix.statics;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MatrixUtils {
	private static ObjectMapper mapper = new ObjectMapper();

	public static String maptToJson(Object object) throws JsonProcessingException {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.writeValueAsString(object);
	}

	public static <T> T mapToObject(String source, TypeReference<T> type)
			throws JsonParseException, JsonMappingException, IOException {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(source, type);
	}

}
