package com.test.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetThresholdMetadataFromMysql {
	
	public List<ThresholdMetadataPOJO> getthresholdmetadata() throws ClassNotFoundException, SQLException {

		List<ThresholdMetadataPOJO> thresholdmetadatalist = new ArrayList<ThresholdMetadataPOJO>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iot", "root", "");
			Statement stmt = conn.createStatement();
			String query = "select * from threshold;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ThresholdMetadataPOJO thresholdmetadata = new ThresholdMetadataPOJO();
				thresholdmetadata.setType_id(rs.getInt("type_id"));
				thresholdmetadata.setType(rs.getString("type"));
				thresholdmetadata.setThresholdvalue_min(rs.getInt("thresholdvalue_min"));
				thresholdmetadata.setThresholdvalue_max(rs.getInt("thresholdvalue_max"));
				thresholdmetadatalist.add(thresholdmetadata);
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return thresholdmetadatalist;
	}
}
