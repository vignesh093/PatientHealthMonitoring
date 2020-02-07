package com.kafkaace.metadata;

public class ResultType {
	
	private int patientid;
	private String name;
	private String phone_number;
	private String emailid;
	private long startms;
	private long endms;
	private long value;
	public ResultType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResultType(int patientid, String name, String phone_number, String emailid, long startms, long endms,
			long value) {
		super();
		this.patientid = patientid;
		this.name = name;
		this.phone_number = phone_number;
		this.emailid = emailid;
		this.startms = startms;
		this.endms = endms;
		this.value = value;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
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
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ResultType [patientid=" + patientid + ", name=" + name + ", phone_number=" + phone_number + ", emailid="
				+ emailid + ", startms=" + startms + ", endms=" + endms + ", value=" + value + "]";
	}
	
	
}
