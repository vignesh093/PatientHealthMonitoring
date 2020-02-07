package com.kafkaace.streamprocess;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

public class ChangeMessageTimestamp {
	static ResourceBundle rb = ResourceBundle.getBundle("ChangeMessageTimestamp");
	public static void main(final String[] args) throws Exception {
		//This job is required because we want to window based on the eventtime got from the message.
		//However in kafka streams there is no way to mention column eventtime while windowing.
		//Hence via this job we replace kafka's timestamp with message eventtime timestamp to do windowing
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, rb.getString("applicationid"));
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, rb.getString("kafka_bootstrap"));
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, EventTimeTimestampExtractor.class);
		props.put(StreamsConfig.consumerPrefix(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG),"latest");
		StreamsBuilder builder = new StreamsBuilder();
		builder.stream(rb.getString("sourcetopic")).to(rb.getString("destinationtopic"));
		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.cleanUp();
		streams.start();
	}

}
