package com.couchbase.demo.iot.vehicles.spark;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import org.apache.spark.SparkConf;
import com.couchbase.spark.japi.CouchbaseSparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import java.util.Arrays;
import java.util.List;

import static com.couchbase.spark.japi.CouchbaseSparkContext.couchbaseContext;
import static com.couchbase.demo.iot.vehicles.cfg.ConfigManager.*;
import static com.couchbase.demo.iot.vehicles.ImportVehicles.*;
import static com.couchbase.spark.japi.CouchbaseDocumentRDD.couchbaseDocumentRDD;
import java.util.Map;

/**
 * Demos how to retrieve an RDD from Spark
 * 
 * Further examples can be found here: https://github.com/couchbaselabs/couchbase-spark-samples/blob/master/src/main/scala/JavaExample.java
 * 
 * @author david
 */
public class CountSamplesByType {
     
    public static final String COUNT_TYPE_V_KEY = "count::type::vehicle";
    
    
    public static void main(String[] args) {
        
        SparkConf conf = new SparkConf()
            .setAppName("CountSamplesByType")
            .setMaster(getSparkConfig().getMaster())
            .set(getSparkConfig().getCbClusterSetting(), getSparkConfig().getCbCluster())
            .set(getSparkConfig().getBucket(),getSparkConfig().getPassword());
        
        JavaSparkContext sc = new JavaSparkContext(conf);
        CouchbaseSparkContext csc = couchbaseContext(sc);
        
        //Example: Extract keys
        List<String> samples = csc.couchbaseGet(Arrays.asList(S_KEY))
                .map(d -> d.content().getArray(S_PROP))
                .flatMap(a -> a.toList())
                .map(k -> { System.out.println(k);return k.toString();})
                .collect();
        
        //Example: Count by 
        Map<String, Long> count =  csc.couchbaseGet(samples)
                .map(d -> d.content().getString("v_type"))
                .countByValue();

       System.out.println("count: " + count.toString());
       
       //Example: Write back to Couchbase
       couchbaseDocumentRDD(
               sc.parallelize(Arrays.asList(JsonDocument.create(COUNT_TYPE_V_KEY, JsonObject.from(count))))
        ).saveToCouchbase();                            
    }    
}
