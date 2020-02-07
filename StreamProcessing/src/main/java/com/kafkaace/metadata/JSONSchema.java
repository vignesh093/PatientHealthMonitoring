package com.kafkaace.metadata;

import java.util.ArrayList;

public class JSONSchema {
		  private String type;
		  ArrayList<JSONFieldsSchema> fields = new ArrayList<JSONFieldsSchema>();
		  private boolean optional;
		  private String name;
		public JSONSchema(String type, ArrayList<JSONFieldsSchema> fields, boolean optional, String name) {
			super();
			this.type = type;
			this.fields = fields;
			this.optional = optional;
			this.name = name;
		}
		public JSONSchema() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public ArrayList<JSONFieldsSchema> getFields() {
			return fields;
		}
		public void setFields(ArrayList<JSONFieldsSchema> fields) {
			this.fields = fields;
		}
		public boolean isOptional() {
			return optional;
		}
		public void setOptional(boolean optional) {
			this.optional = optional;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}


		  
}
