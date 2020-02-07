package com.kafkaace.streamprocess;

import java.sql.Timestamp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;



public class EventTimeTimestampExtractor implements TimestampExtractor{

	@Override
	public long extract(ConsumerRecord<Object, Object> record, long previousTimestamp) {
		String value = record.value().toString();
		long newtimestamp = Timestamp.valueOf(value.split(",")[3]).getTime();
		return newtimestamp;
	}
	
	

}
