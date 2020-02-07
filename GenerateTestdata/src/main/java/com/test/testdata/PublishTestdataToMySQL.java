package com.test.testdata;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.test.db.PushTestdatatoMysql;
import com.test.kafka.PushTestdatatoKafka;
import com.test.metadata.GetPatientMetadataFromMysql;
import com.test.metadata.GetThresholdMetadataFromMysql;
import com.test.metadata.PatientMetadataPOJO;
import com.test.metadata.ThresholdMetadataPOJO;

public class PublishTestdataToMySQL {
	static Random rand = new Random();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		PushTestdatatoMysql pushmysql = new PushTestdatatoMysql();
		//Get patient metadata from MYSQL which contains personal info about the patients
		GetPatientMetadataFromMysql patientmata = new GetPatientMetadataFromMysql();
		//Get pre-configured threshold for each parameter type from MYSQL
		GetThresholdMetadataFromMysql thresholdmeta = new GetThresholdMetadataFromMysql();
		List<PatientMetadataPOJO> patientmetaoutput = patientmata.getpatientmetadata();
		
		List<ThresholdMetadataPOJO> thresholdmetaoutput = thresholdmeta.getthresholdmetadata();

		int count = 0;
		int value_count = 0;
		int date_count = 0;
		//For every patient 3 parameters data will be generated. So if we have a count as 100, then 300 records will be
		//generated in total
		while (count < 100) {
			for (PatientMetadataPOJO patient : patientmetaoutput) {
				for (ThresholdMetadataPOJO threshold : thresholdmetaoutput) {
					int value = 0;
					Timestamp timestamp = new Timestamp(new Date().getTime());
					//For every 4 records generate one anamoly record which is outside the configured threshold
					if (value_count % 4 == 0) {
						value = rand.nextInt((threshold.getThresholdvalue_max() + 20) - threshold.getThresholdvalue_max()) + threshold.getThresholdvalue_max();
					}
					else {
						value = rand.nextInt(threshold.getThresholdvalue_max() - threshold.getThresholdvalue_min()) + threshold.getThresholdvalue_min();
					}

					if (date_count % 3 == 0) {
						timestamp = new Timestamp(new Date().getTime());
						int minute_delay = (-1) * rand.nextInt(10); // send some record with eventtime delay(max delay of 10 minutes) (1 record for every 3 records)
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MINUTE, minute_delay);

						timestamp = new Timestamp(cal.getTime().getTime());
					}

					StringBuffer sb = new StringBuffer();
					sb.append(patient.getPatientid());
					sb.append(",");
					sb.append(patient.getName());
					sb.append(",");
					sb.append(threshold.getType_id());
					sb.append(",");
					sb.append(value);
					sb.append(",");
					sb.append(timestamp);
					//Push the generated test data to MYSQL
					pushmysql.loadtomysql(sb.toString());
					count++;
					date_count++;
					value_count++;
				}

			}

		}
	}

}
