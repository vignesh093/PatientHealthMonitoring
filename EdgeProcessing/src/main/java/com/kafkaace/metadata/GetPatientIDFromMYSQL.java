package com.kafkaace.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class GetPatientIDFromMYSQL {
	//Retrieve the loaded generated patient information from MYSQL and save that to a ArrayList
	public List<Patient> getpatientmetadata() throws ClassNotFoundException, SQLException {

		List<Patient> patientmetadatalist = new ArrayList<Patient>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iot", "root", "");
			Statement stmt = conn.createStatement();
			String query = "select * from patienttsdata;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Patient patientmetadata = new Patient();
				patientmetadata.setPatientid(rs.getInt("patientid"));
				patientmetadata.setName(rs.getString("name"));
				patientmetadata.setTypeid(rs.getInt("typeid"));
				patientmetadata.setValue(rs.getInt("value"));
				patientmetadata.setEventtime(rs.getString("eventtime"));
				patientmetadatalist.add(patientmetadata);
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return patientmetadatalist;
	}
	
    public static List<PatientId> toPatientIds(List<Patient> patients) {
        return patients.stream()
            .map(patient -> new PatientId(patient.getPatientid()))
            .collect(Collectors.toList());
    }
}
