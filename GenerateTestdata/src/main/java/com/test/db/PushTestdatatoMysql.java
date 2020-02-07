package com.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PushTestdatatoMysql {
	//Loads the generated record to mysql
	public void loadtomysql(String value) throws SQLException {
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iot", "root", "");
	   PreparedStatement pst = con.prepareStatement("insert into patienttsdata(patientid,name,typeid,value,eventtime) values(?,?,?,?,?)");
	  String[] split_value = value.split(",");
	  pst.setInt(1,Integer.parseInt(split_value[0]));
	      pst.setString(2, split_value[1]);
	      pst.setInt(3, Integer.parseInt(split_value[2]));
	      pst.setInt(4, Integer.parseInt(split_value[3]));
	      pst.setString(5, split_value[4]);
	      int i = pst.executeUpdate();
	      if(i != 0) {
	    	  System.out.println("Record Inserted");
	      }
	}
	
}
