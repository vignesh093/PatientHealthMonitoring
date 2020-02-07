package com.kafkaace.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TupleSerializer implements Serializer<AggTuple>  {

	@Override
	  public void configure(Map<String, ?> configs, boolean isKey) {
	  }

	  @Override
	  public void close() {
	  }

	  @Override
	  public byte[] serialize(String topic, AggTuple typeagg) {
		  byte[] retVal = null;
		    ObjectMapper objectMapper = new ObjectMapper();
		    try {
		      retVal = objectMapper.writeValueAsString(typeagg).getBytes();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return retVal;
	  }
}
