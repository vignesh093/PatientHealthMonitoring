package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaace.metadata.ResultType;

public class ResultTypeDeserializer implements Deserializer<ResultType> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public void close() {
	}

	@Override
	public ResultType deserialize(String topic, byte[] bytes) {

		ObjectMapper mapper = new ObjectMapper();
		ResultType res = null;
		try {
			res = mapper.readValue(bytes, ResultType.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

}
