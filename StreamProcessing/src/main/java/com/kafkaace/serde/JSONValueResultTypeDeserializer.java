package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaace.metadata.JSONValueResultType;

public class JSONValueResultTypeDeserializer implements Deserializer<JSONValueResultType> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public void close() {
	}

	@Override
	public JSONValueResultType deserialize(String topic, byte[] bytes) {

		ObjectMapper mapper = new ObjectMapper();
		JSONValueResultType res = null;
		try {
			res = mapper.readValue(bytes, JSONValueResultType.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

}
