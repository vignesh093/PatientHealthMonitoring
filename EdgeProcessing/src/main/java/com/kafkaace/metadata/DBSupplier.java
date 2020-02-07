package com.kafkaace.metadata;

import java.sql.SQLException;

import org.apache.edgent.function.Supplier;

public class DBSupplier implements Supplier<Iterable<Patient>> {

	@Override
	public Iterable<Patient> get() {
 		Iterable<Patient> patientdata = null;
		GetPatientIDFromMYSQL getpatientdata = new GetPatientIDFromMYSQL();
		try {
			patientdata = getpatientdata.getpatientmetadata();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientdata;
	}
	
	

}
