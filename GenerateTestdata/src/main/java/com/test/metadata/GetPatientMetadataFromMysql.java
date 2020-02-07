package com.test.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetPatientMetadataFromMysql {
	public List<PatientMetadataPOJO> getpatientmetadata() throws ClassNotFoundException, SQLException {

		List<PatientMetadataPOJO> patientmetadatalist = new ArrayList<PatientMetadataPOJO>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iot", "root", "");
			Statement stmt = conn.createStatement();
			String query = "select * from patient;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				PatientMetadataPOJO patientmetadata = new PatientMetadataPOJO();
				patientmetadata.setPatientid(rs.getInt("patientid"));
				patientmetadata.setName(rs.getString("name"));
				patientmetadata.setAge(rs.getInt("age"));
				patientmetadata.setPhone_number(rs.getLong("phone_number"));
				patientmetadata.setEmailid(rs.getString("emailid"));
				patientmetadatalist.add(patientmetadata);
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return patientmetadatalist;
	}
}
