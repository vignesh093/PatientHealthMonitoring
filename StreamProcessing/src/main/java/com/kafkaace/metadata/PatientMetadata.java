package com.kafkaace.metadata;

public class PatientMetadata {

	private int patientid;
	private String name;
	private int age;
	private String phone_number;
	private String emailid;
	
	public PatientMetadata() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientMetadata(int patientid, String name, int age, String phone_number, String emailid) {
		super();
		this.patientid = patientid;
		this.name = name;
		this.age = age;
		this.phone_number = phone_number;
		this.emailid = emailid;
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

	public String getphone_number() {
		return phone_number;
	}

	public void setphone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "PatientMetadata [patientid=" + patientid + ", name=" + name + ", age=" + age + ", phone_number=" + phone_number
				+ ", emailid=" + emailid + "]";
	}
	
	
	
}	
