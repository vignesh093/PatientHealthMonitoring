package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaace.metadata.ResultType;

public class ResultTypeSerializer implements Serializer<ResultType> {
	@Override
	  public void configure(Map<String, ?> configs, boolean isKey) {
	  }

	  @Override
	  public void close() {
	  }

	  @Override
	  public byte[] serialize(String topic, ResultType aggres) {
		  byte[] retVal = null;
		    ObjectMapper objectMapper = new ObjectMapper();
		    try {
		      retVal = objectMapper.writeValueAsString(aggres).getBytes();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return retVal;
	  }

}
