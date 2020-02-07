package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaace.metadata.JSONValueResultType;

public class JSONValueResultTypeSerializer implements Serializer<JSONValueResultType> {
	@Override
	  public void configure(Map<String, ?> configs, boolean isKey) {
	  }

	  @Override
	  public void close() {
	  }

	  @Override
	  public byte[] serialize(String topic, JSONValueResultType jsonres) {
		  byte[] retVal = null;
		    ObjectMapper objectMapper = new ObjectMapper();
		    try {
		      retVal = objectMapper.writeValueAsString(jsonres).getBytes();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return retVal;
	  }

}
