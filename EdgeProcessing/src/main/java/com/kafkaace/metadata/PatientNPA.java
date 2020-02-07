package com.kafkaace.metadata;

public class PatientNPA {
	

	private int patientid;
	private int typeid;
	private int value;
	private String eventtime;
	
	public PatientNPA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientNPA(int patientid, int typeid, int value, String eventtime) {
		super();
		this.patientid = patientid;
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

	@Override
	public String toString() {
		return patientid + "," + typeid + "," + value + "," + eventtime;
	}
	
	
}
