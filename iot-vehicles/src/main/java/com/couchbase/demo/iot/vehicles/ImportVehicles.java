package com.couchbase.demo.iot.vehicles;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.demo.iot.vehicles.conn.BucketFactory;
import com.couchbase.demo.iot.vehicles.model.Vehicle;

/**
 * Imports 1Mio vehicles
 *
 * @author david
 */
public class ImportVehicles {

    private static final int NUM_SAMPLES = 1000;
    private static final int NUM_TOTAL = 10000; 
    public static final String V_PREFIX = "vehicle::";
    public static final String S_KEY = "samples::vehicle";
    public static final String S_PROP = "samples";
    
    
    public static void main(String[] args) {

       
        JsonArray samplesArr = JsonArray.create();
        
        //First import
        for (int i = 0; i < NUM_SAMPLES; i++) {
            
            Vehicle v = new Vehicle();
            String key = V_PREFIX + v.getId();
            
            samplesArr.add(key);  
            BucketFactory.getBucket().insert(RawJsonDocument.create(key, v.toString()));
            System.out.println(v.toString());
        }
        
        JsonObject samples = JsonObject.create();
        samples.put(S_PROP, samplesArr);
        BucketFactory.getBucket().insert(JsonDocument.create(S_KEY, samples));
        
        //Second import
        for (int i = 0; i < NUM_TOTAL - NUM_SAMPLES; i++) {
        
            Vehicle v = new Vehicle();
            String key = V_PREFIX + v.getId();
            BucketFactory.getBucket().insert(RawJsonDocument.create(key, v.toString()));
            System.out.println(v.toString());
           
        }
        
    }

}
