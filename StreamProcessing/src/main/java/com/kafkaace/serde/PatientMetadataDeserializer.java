package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaace.metadata.PatientMetadata;

public class PatientMetadataDeserializer implements Deserializer<PatientMetadata>  {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public void close() {
	}

	@Override
	public PatientMetadata deserialize(String topic, byte[] bytes) {

		ObjectMapper mapper = new ObjectMapper();
		PatientMetadata patmeta = null;
		try {
			patmeta = mapper.readValue(bytes, PatientMetadata.class);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return patmeta;
	}

}
