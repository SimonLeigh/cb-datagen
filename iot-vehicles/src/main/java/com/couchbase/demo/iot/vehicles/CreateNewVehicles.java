
package com.couchbase.demo.iot.vehicles;

import com.couchbase.client.java.document.BinaryDocument;
import com.couchbase.client.java.document.Document;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.demo.iot.vehicles.conn.BucketFactory;
import com.couchbase.demo.iot.vehicles.model.City;
import com.couchbase.demo.iot.vehicles.model.Vehicle;

/**
 * Continously creates new vehicles
 * 
 * @author david
 */
public class CreateNewVehicles {
    
    public static void main(String[] args) throws InterruptedException {
        
        for (int i = 0; i < 1000; i++) {
         
            Vehicle v = new Vehicle();
            v.setLocation(new City("Munich", 48.13f, 11.58f));
            
            System.out.println(v.toString());
            
            BucketFactory.getBucket().upsert(RawJsonDocument.create("vehicle::" + v.getId(), v.toString()));
                      
            //Thread.sleep(1000);
            
        }        
    }
}
