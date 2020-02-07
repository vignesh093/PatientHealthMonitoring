package com.kafkaace.metadata;

public class Patient {
	
	private int patientid;
	private String name;
	private int typeid;
	private int value;
	private String eventtime;
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(int patientid, String name, int typeid, int value, String eventtime) {
		super();
		this.patientid = patientid;
		this.name = name;
		this.typeid = typeid;
		this.value = value;
		this.eventtime = eventtime;
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

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getEventtime() {
		return eventtime;
	}

	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}
	

	
}
