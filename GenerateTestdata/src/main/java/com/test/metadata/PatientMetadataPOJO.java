package com.test.metadata;

public class PatientMetadataPOJO {
		
	private int patientid;
	private String name;
	private int age;
	private long phone_number;
	private String emailid;
	public PatientMetadataPOJO(int patientid, String name, int age, long phone_number, String emailid) {
		super();
		this.patientid = patientid;
		this.name = name;
		this.age = age;
		this.phone_number = phone_number;
		this.emailid = emailid;
	}
	public PatientMetadataPOJO() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	
	
	
}
