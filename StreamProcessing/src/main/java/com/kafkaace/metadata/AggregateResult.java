package com.kafkaace.metadata;

public class AggregateResult {
	private int typeid;
	private long startms;
	private long endms;
	private String key;
	private long value;
	public AggregateResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AggregateResult(int typeid, long startms, long endms, String key, long value) {
		super();
		this.typeid = typeid;
		this.startms = startms;
		this.endms = endms;
		this.key = key;
		this.value = value;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public long getStartms() {
		return startms;
	}
	public void setStartms(long startms) {
		this.startms = startms;
	}
	public long getEndms() {
		return endms;
	}
	public void setEndms(long endms) {
		this.endms = endms;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "AggregateResult [typeid=" + typeid + ", startms=" + startms + ", endms=" + endms + ", key=" + key
				+ ", value=" + value + "]";
	}
	
}
