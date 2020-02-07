package com.kafkaace.metadata;

public class JSONFieldsSchema {
	private String type;
	private Boolean optional;
	private String field;
	public JSONFieldsSchema(String type, Boolean optional, String field) {
		super();
		this.type = type;
		this.optional = optional;
		this.field = field;
	}
	public JSONFieldsSchema() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getOptional() {
		return optional;
	}
	public void setOptional(Boolean optional) {
		this.optional = optional;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	@Override
	public String toString() {
		return "JSONFieldsSchema [type=" + type + ", optional=" + optional + ", field=" + field + "]";
	}
	
	
}
