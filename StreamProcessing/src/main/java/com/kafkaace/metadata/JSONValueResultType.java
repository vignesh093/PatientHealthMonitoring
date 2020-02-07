package com.kafkaace.metadata;

public class JSONValueResultType {
	JSONSchema SchemaObject;
	JSONPayload PayloadObject;


		 // Getter Methods 

		  public JSONSchema getSchema() {
		    return SchemaObject;
		  }

		  public JSONPayload getPayload() {
		    return PayloadObject;
		  }

		 // Setter Methods 

		  public void setSchema( JSONSchema schemaObject ) {
		    this.SchemaObject = schemaObject;
		  }

		  public void setPayload( JSONPayload payloadObject ) {
		    this.PayloadObject = payloadObject;
		  }
		}
	
