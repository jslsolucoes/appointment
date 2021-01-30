package com.jslsolucoes.appointment.api.resource;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PhoneDeserializer extends JsonDeserializer<Long> {

	private String normalize(String value) {
		return value.replaceAll("-|\\(|\\)|\\s", "");
	}

	@Override
	public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		return Long.valueOf(normalize(jsonParser.getValueAsString()));
	}

}