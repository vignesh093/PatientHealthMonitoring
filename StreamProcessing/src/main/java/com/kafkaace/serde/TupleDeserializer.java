package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TupleDeserializer implements Deserializer<AggTuple>  {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public void close() {
	}

	@Override
	public AggTuple deserialize(String topic, byte[] bytes) {

		ObjectMapper mapper = new ObjectMapper();
		AggTuple typeagg = null;
		try {
			typeagg = mapper.readValue(bytes, AggTuple.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return typeagg;
	}
}
