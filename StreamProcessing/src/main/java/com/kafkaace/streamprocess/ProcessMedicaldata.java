package com.kafkaace.streamprocess;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Reducer;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.TimeWindowedKStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.kstream.Windowed;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaace.metadata.AggregateResult;
import com.kafkaace.metadata.JSONFieldsSchema;
import com.kafkaace.metadata.JSONPayload;
import com.kafkaace.metadata.JSONSchema;
import com.kafkaace.metadata.JSONValueResultType;
import com.kafkaace.metadata.PatientMetadata;
import com.kafkaace.metadata.ResultType;
import com.kafkaace.serde.AggTuple;
import com.kafkaace.serde.AggregateResultDeserializer;
import com.kafkaace.serde.AggregateResultSerializer;
import com.kafkaace.serde.JSONValueResultTypeDeserializer;
import com.kafkaace.serde.JSONValueResultTypeSerializer;
import com.kafkaace.serde.PatientMetadataDeserializer;
import com.kafkaace.serde.PatientMetadataSerializer;
import com.kafkaace.serde.ResultTypeDeserializer;
import com.kafkaace.serde.ResultTypeSerializer;
import com.kafkaace.serde.TupleDeserializer;
import com.kafkaace.serde.TupleSerializer;

@SuppressWarnings("deprecation")
public class ProcessMedicaldata {
	
	static ResourceBundle rb = ResourceBundle.getBundle("KafkaaceStreamProcessing");
	
	public static KeyValue<Integer, AggregateResult> getresult(Windowed<String> k, AggTuple v) {
		AggregateResult aggres = new AggregateResult();
		aggres.setKey(k.key());
		aggres.setStartms(k.window().start());
		aggres.setEndms(k.window().end());
		aggres.setValue((v.sum / v.count));
		aggres.setTypeid(Integer.parseInt(k.key().split(",")[1]));
		Integer patientid = Integer.parseInt(k.key().split(",")[0]);
		return new KeyValue<Integer, AggregateResult>(patientid, aggres);
	}

	public static PatientMetadata stringtojson(String value)
			throws JsonParseException, JsonMappingException, IOException {
		PatientMetadata pmeta = new ObjectMapper().readValue(value, PatientMetadata.class);
		return pmeta;
	}
	
	public static ArrayList<JSONFieldsSchema> getfieldsschema() {
		ArrayList<JSONFieldsSchema> fieldstobeadded = new ArrayList<JSONFieldsSchema>();
		fieldstobeadded.add(new JSONFieldsSchema("int64",false,"patientid"));
		fieldstobeadded.add(new JSONFieldsSchema("string",false,"name"));
		fieldstobeadded.add(new JSONFieldsSchema("string",false,"phone_number"));
		fieldstobeadded.add(new JSONFieldsSchema("string",false,"emailid"));
		fieldstobeadded.add(new JSONFieldsSchema("int64",false,"startime"));
		fieldstobeadded.add(new JSONFieldsSchema("int64",false,"endtime"));
		fieldstobeadded.add(new JSONFieldsSchema("int64",false,"value"));
		return fieldstobeadded;
	}
	
	public static JSONValueResultType generatefinaljson(ResultType restype) {
		JSONSchema jsonschema = new JSONSchema();
		ArrayList<JSONFieldsSchema> fieldsschema = getfieldsschema();
		jsonschema.setName("alerts");
		jsonschema.setOptional(false);
		jsonschema.setType("struct");
		jsonschema.setFields(fieldsschema);
		JSONPayload jsonpayload = new JSONPayload();
		jsonpayload.setName(restype.getName());
		jsonpayload.setPatientid(restype.getPatientid());
		jsonpayload.setPhone_number(restype.getPhone_number());
		jsonpayload.setEmailid(restype.getEmailid());
		jsonpayload.setStartime(restype.getStartms());
		jsonpayload.setEndtime(restype.getEndms());
		jsonpayload.setValue(restype.getValue());
		JSONValueResultType finaljson = new JSONValueResultType();
		finaljson.setSchema(jsonschema);
		finaljson.setPayload(jsonpayload);
		return finaljson;
	}

	@SuppressWarnings({ "restriction", "unchecked" })
	public static void main(final String[] args) throws Exception {
		Map<String, Object> serdeProps = new HashMap<>();
		Properties props = new Properties();	
		//Kafka config
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, rb.getString("applicationid"));
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, rb.getString("kafka_bootstrap"));
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put("default.deserialization.exception.handler", LogAndContinueExceptionHandler.class);
		
		//Get the threshold values from the property file
		int heart_avg = Integer.parseInt(rb.getString("heart_avg_value"));
		int bp_avg = Integer.parseInt(rb.getString("bp_avg_value"));
		int resp_avg = Integer.parseInt(rb.getString("resp_avg_value"));
		
		//Configure required SerDe's
		Serializer<AggTuple> typeSerializer = new TupleSerializer();
		Deserializer<AggTuple> typeDeserializer = new TupleDeserializer();
		Serde<AggTuple> typeAggSerde = Serdes.serdeFrom(typeSerializer, typeDeserializer);

		Serializer<AggregateResult> aggrestypeSerializer = new AggregateResultSerializer();
		Deserializer<AggregateResult> aggrestypeDeserializer = new AggregateResultDeserializer();
		Serde<AggregateResult> aggresserde = Serdes.serdeFrom(aggrestypeSerializer, aggrestypeDeserializer);

		Serializer<PatientMetadata> patmetaSerializer = new PatientMetadataSerializer();
		Deserializer<PatientMetadata> patmetaDeserializer = new PatientMetadataDeserializer();
		Serde<PatientMetadata> patmetaserde = Serdes.serdeFrom(patmetaSerializer, patmetaDeserializer);
		
		Serializer<ResultType> resserializer = new ResultTypeSerializer();
		Deserializer<ResultType> resdeserializer = new ResultTypeDeserializer();
		Serde<ResultType> reserde = Serdes.serdeFrom(resserializer, resdeserializer);
		
		Serializer<JSONValueResultType> jsonresserializer = new JSONValueResultTypeSerializer();
		Deserializer<JSONValueResultType> jsonresdeserializer = new JSONValueResultTypeDeserializer();
		Serde<JSONValueResultType> jsonreserde = Serdes.serdeFrom(jsonresserializer, jsonresdeserializer);
		
		StreamsBuilder builder = new StreamsBuilder();
		//Consume data from patientdatatopic which was published by edgeprocessing module
		KStream<String, String> patientsdata = builder.stream(rb.getString("patientdatatopic"));
		//Get the metadata of all patients from metadatatopic
		KStream<Integer, String> patientmetadata = builder.stream(rb.getString("metadatatopic"),
				Consumed.with(Serdes.Integer(), Serdes.String()));

		//GlobalKTable is not possible because kafka connect with Integer as key with Transformation is not working properly.
		//When we stream a single key is getting populated instead of all available keys. However, console consumer on that topic works fine.
		//Hence we have used KStreams and hence selectkey is required to populate the key from the value column.
		@SuppressWarnings("deprecation")
		KTable<Integer, String> patientmetadaktable = patientmetadata
				.selectKey(new KeyValueMapper<Integer, String, Integer>() {
					@Override
					public Integer apply(Integer key, String value) {
						try {
							PatientMetadata newvalue = stringtojson(value);
							key = newvalue.getPatientid();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println("key " + key);
						return key;
					}
				}).groupByKey(Serialized.with(Serdes.Integer(), Serdes.String())).reduce(new Reducer<String>() { //GroupByKey is required to convert KStream to KTable
					//KTable is required beacuse a Join cannot happen between KTable and KTable.Hence we convert the KStream to KTable
					@Override
					public String apply(String aggValue, String newValue) {
						return newValue;
					}
				});
		
		//map String values to Java POJO
		KTable<Integer, PatientMetadata> patientmetamapped = patientmetadaktable 
				.mapValues(new ValueMapper<String, PatientMetadata>() {
					@Override
					public PatientMetadata apply(String value) {
						try {
							return stringtojson(value);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
				});

		KStream<String, String> datawithgroupingkey = patientsdata
				.map((k, v) -> new KeyValue<String, String>((k + "," + v.split(",")[1]), v));

		KGroupedStream<String, String> groupedkey = datawithgroupingkey.groupByKey();
		
		//Run a window for every one minute and aggregate the values generated for every parameter. 
		//A record that is late by atmost 10 minutes is accepted
		
		TimeWindowedKStream<String, String> windowedmessages = groupedkey
				.windowedBy(TimeWindows.of(Duration.ofMinutes(1)).grace(Duration.ofMinutes(10)));
		KTable<Windowed<String>, AggTuple> aggregates = windowedmessages
				.aggregate(() -> new AggTuple(0, 0),
						(aggKey, newValue, aggValue) -> new AggTuple(
								(aggValue.sum + Integer.parseInt(newValue.split(",")[2])), aggValue.count + 1),
						Materialized.with(Serdes.String(), typeAggSerde));
		
		//If the aggregate is above the threshold filter those records
		KStream<Integer, AggregateResult> mappedaggregateres = aggregates.toStream().map((k, v) -> getresult(k, v))
				.filter((k, v) -> {
					if (v.getTypeid() == 1 && v.getValue() > heart_avg)
						return false;
					else if (v.getTypeid() == 2 && v.getValue() > bp_avg)
						return false;
					else if (v.getTypeid() == 3 && v.getValue() > resp_avg)
						return false;
					else
						return true;
				}).selectKey((k, v) -> k);
		
		//Get patient personal information before loading the anamoly records into MYSQL
		KStream<Integer, ResultType> joinedres = mappedaggregateres.leftJoin(patientmetamapped,
				new ValueJoiner<AggregateResult, PatientMetadata, ResultType>() {
					@Override
					public ResultType apply(final AggregateResult kstreamvalue, final PatientMetadata globalvalue) {
						return new ResultType(globalvalue.getPatientid(), globalvalue.getName(),
								globalvalue.getphone_number(), globalvalue.getEmailid(), kstreamvalue.getStartms(),
								kstreamvalue.getEndms(),kstreamvalue.getValue());
					}
				}, Joined.with(Serdes.Integer(), aggresserde, patmetaserde));
		KStream<Integer, JSONValueResultType> finaljsonres = joinedres.map((k,v) -> new KeyValue<Integer,JSONValueResultType>(k,generatefinaljson(v)));
		
		//Produce the anamoly records to a Kafka topic
		Produced<Integer, JSONValueResultType> finalprodtype = Produced.with(Serdes.Integer(),jsonreserde);
		finaljsonres.to(rb.getString("sendalerts"), finalprodtype);
		
		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.cleanUp();
		streams.start();
	}
}
