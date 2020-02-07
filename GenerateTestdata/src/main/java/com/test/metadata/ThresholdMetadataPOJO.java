package com.test.metadata;

public class ThresholdMetadataPOJO {
		
		private int type_id;
		private String type;
		private int thresholdvalue_min;
		private int thresholdvalue_max;
		public ThresholdMetadataPOJO(int type_id, String type, int thresholdvalue_min, int thresholdvalue_max) {
			super();
			this.type_id = type_id;
			this.type = type;
			this.thresholdvalue_min = thresholdvalue_min;
			this.thresholdvalue_max = thresholdvalue_max;
		}
		public ThresholdMetadataPOJO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getType_id() {
			return type_id;
		}
		public void setType_id(int type_id) {
			this.type_id = type_id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getThresholdvalue_min() {
			return thresholdvalue_min;
		}
		public void setThresholdvalue_min(int thresholdvalue_min) {
			this.thresholdvalue_min = thresholdvalue_min;
		}
		public int getThresholdvalue_max() {
			return thresholdvalue_max;
		}
		public void setThresholdvalue_max(int thresholdvalue_max) {
			this.thresholdvalue_max = thresholdvalue_max;
		}
		
		
}
