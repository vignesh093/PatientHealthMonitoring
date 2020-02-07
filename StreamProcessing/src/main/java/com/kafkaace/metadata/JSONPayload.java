package com.kafkaace.metadata;

public class JSONPayload {
	
		  private float patientid;
		  private String name;
		  private String phone_number;
		  private String emailid;
		  private float startime;
		  private float endtime;
		  private float value;


		 // Getter Methods 

		  public float getPatientid() {
		    return patientid;
		  }

		  public String getName() {
		    return name;
		  }

		  public String getPhone_number() {
		    return phone_number;
		  }

		  public String getEmailid() {
		    return emailid;
		  }

		  public float getStartime() {
		    return startime;
		  }

		  public float getEndtime() {
		    return endtime;
		  }

		  public float getValue() {
		    return value;
		  }

		 // Setter Methods 

		  public void setPatientid( float patientid ) {
		    this.patientid = patientid;
		  }

		  public void setName( String name ) {
		    this.name = name;
		  }

		  public void setPhone_number( String phone_number ) {
		    this.phone_number = phone_number;
		  }

		  public void setEmailid( String emailid ) {
		    this.emailid = emailid;
		  }

		  public void setStartime( float startime ) {
		    this.startime = startime;
		  }

		  public void setEndtime( float endtime ) {
		    this.endtime = endtime;
		  }

		  public void setValue( float value ) {
		    this.value = value;
		  }
		
}
