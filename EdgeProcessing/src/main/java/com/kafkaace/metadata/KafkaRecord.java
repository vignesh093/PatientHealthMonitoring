package com.kafkaace.metadata;

public class KafkaRecord {
	
	private String topic;
	private int partition;
	private String key;
	private String value;
	public KafkaRecord(String topic, int partition, String key, String value) {
		super();
		this.topic = topic;
		this.partition = partition;
		this.key = key;
		this.value = value;
	}
	public KafkaRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getPartition() {
		return partition;
	}
	public void setPartition(int partition) {
		this.partition = partition;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
    
    

}
