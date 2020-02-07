package com.kafkaace.metadata;

public class PatientId {
	private int id;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PatientId(int id) {
        this.id = id;
    }
    public String toString() {
        return String.format("id=%d", id);
    }
}
