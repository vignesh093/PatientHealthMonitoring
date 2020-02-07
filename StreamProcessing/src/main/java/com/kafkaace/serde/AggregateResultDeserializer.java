package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaace.metadata.AggregateResult;

public class AggregateResultDeserializer implements Deserializer<AggregateResult>  {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public void close() {
	}

	@Override
	public AggregateResult deserialize(String topic, byte[] bytes) {

		ObjectMapper mapper = new ObjectMapper();
		AggregateResult aggres = null;
		try {
			aggres = mapper.readValue(bytes, AggregateResult.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return aggres;
	}
}
