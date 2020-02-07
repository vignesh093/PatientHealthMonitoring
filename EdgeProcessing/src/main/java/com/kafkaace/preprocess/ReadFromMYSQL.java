package com.kafkaace.preprocess;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.edgent.connectors.kafka.KafkaProducer;
import org.apache.edgent.function.Supplier;
import org.apache.edgent.providers.direct.DirectProvider;
import org.apache.edgent.topology.TStream;
import org.apache.edgent.topology.Topology;

import com.kafkaace.metadata.DBSupplier;
import com.kafkaace.metadata.GetPatientIDFromMYSQL;
import com.kafkaace.metadata.KafkaRecord;
import com.kafkaace.metadata.Patient;
import com.kafkaace.metadata.PatientNPA;

public class ReadFromMYSQL {
	GetPatientIDFromMYSQL getpatientdata = new GetPatientIDFromMYSQL();
	Supplier<Iterable<Patient>> dbsupplier = new DBSupplier();
	Properties prop = new Properties();
    ResourceBundle rb = ResourceBundle.getBundle("KafkaaceEdgent");
    
	//Odd patientid will land in one partition and the even patient id will land in different partition
	public int getpartition(int patientid) {
		if ((patientid % 2) == 0)
			return 1;
		else
			return 0;
	}
	
	public String getvalue(PatientNPA npa) {
		String value = npa.getPatientid() + "," + npa.getTypeid() + "," + npa.getValue() + "," + npa.getEventtime();
		return value;
		
	}
	private Map<String, Object> createKafkaConfig() {
		Map<String, Object> kafkaConfig = new HashMap<>();
		kafkaConfig.put("bootstrap.servers", rb.getString("kafka_bootstrap"));
		kafkaConfig.put("message.send.max.retries", rb.getString("max_retries"));
		kafkaConfig.put("enable.idempotence", "True");
		return kafkaConfig;
	}

	private void run() throws Exception {
		DirectProvider tp = new DirectProvider();
		//Retrieve the threshold values set in the property file
		int heart_avg = Integer.parseInt(rb.getString("heart_avg_value"));
		int bp_avg = Integer.parseInt(rb.getString("bp_avg_value"));
		int resp_avg = Integer.parseInt(rb.getString("resp_avg_value"));
		
		//Initialize a new topology
		Topology t = tp.newTopology("ReadfromMySQL");
		
		//Create a stream from the retrieved patient data
		TStream<Patient> patientsource = t.source(dbsupplier);
		
		//Basic edge filtering
		TStream<PatientNPA> patientsnpa = patientsource
				.map(pt -> new PatientNPA(pt.getPatientid(), pt.getTypeid(), pt.getValue(), pt.getEventtime()));
		TStream<PatientNPA> filteredpatients = patientsnpa.filter(npa -> {
			if (npa.getTypeid() == 1 && npa.getValue() > heart_avg - 2 && npa.getValue() < heart_avg + 2)
				return false;
			else if (npa.getTypeid() == 2 && npa.getValue() > bp_avg - 2 && npa.getValue() < bp_avg + 2)
				return false;
			else if (npa.getTypeid() == 3 && npa.getValue() > resp_avg - 2 && npa.getValue() < resp_avg + 2)
				return false;
			else
				return true;
		});
		
		patientsnpa.sink(pt -> System.out.println("retrieved person: " + pt.toString()));

		Map<String, Object> kafkaConfig = createKafkaConfig();
		KafkaProducer producer = new KafkaProducer(t, () -> kafkaConfig);
		
		//Remove personal information from all the records
		TStream<KafkaRecord> kafkarecord = filteredpatients.map(
				npa -> new KafkaRecord("iot2", getpartition(npa.getPatientid()), ( String.valueOf(npa.getPatientid()) +"," + npa.getEventtime()), getvalue(npa)));
		
		//publish the records to kafka
		producer.publish(kafkarecord, rec -> rec.getKey(), rec -> rec.getValue(), rec -> rec.getTopic(),
				rec -> rec.getPartition());
		

		tp.submit(t);

	}
	

	public static void main(String[] args) throws Exception {
		ReadFromMYSQL reader = new ReadFromMYSQL();
		reader.run();
	}


}
