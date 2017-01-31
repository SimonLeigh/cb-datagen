package com.couchbase.demo.iot.vehicles.spark;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import org.apache.spark.SparkConf;
import com.couchbase.spark.japi.CouchbaseSparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import java.util.Arrays;

import static com.couchbase.spark.japi.CouchbaseSparkContext.couchbaseContext;
import static com.couchbase.demo.iot.vehicles.cfg.ConfigManager.*;
import static com.couchbase.spark.japi.CouchbaseDocumentRDD.couchbaseDocumentRDD;
import java.util.HashMap;
import java.util.Map;

/**
 * Demos how to retrieve an RDD from Spark
 * 
 * Further examples can be found here: https://github.com/couchbaselabs/couchbase-spark-samples/blob/master/src/main/scala/JavaExample.java
 * 
 * @author david
 */
public class QueryByCity {
     
    public static final String COUNT_TYPE_C_KEY = "count::type::city";
    
    
    public static void main(String[] args) {
        
        SparkConf conf = new SparkConf()
            .setAppName("QueryByCity")
            .setMaster(getSparkConfig().getMaster())
            .set(getSparkConfig().getCbClusterSetting(), getSparkConfig().getCbCluster())
            .set(getSparkConfig().getBucket(),getSparkConfig().getPassword());
        
        JavaSparkContext sc = new JavaSparkContext(conf);
        CouchbaseSparkContext csc = couchbaseContext(sc);
        
        //Example: N1QL Query
        Map<String, Long> counts = csc.couchbaseQuery(N1qlQuery.simple("SELECT location.name, COUNT(location.name) AS count FROM vehicles WHERE type = 'vehicle' GROUP BY location.name"))
                    .map(r->{
                               JsonObject v = r.value();
                               String name = v.getString("name");
                               long count = v.getLong("count");
                               
                               Map<String, Long> obj = new HashMap<>();
                               obj.put(name, count);
                                
                               return obj;
                    })
                    .reduce( (x,y) -> { 
                       x.putAll(y);
                       return x;
                    });
                    
        System.out.println("Counts: " + counts.toString());   
       
       //Example: Write back to Couchbase
       couchbaseDocumentRDD(
               sc.parallelize(Arrays.asList(JsonDocument.create(COUNT_TYPE_C_KEY, JsonObject.from(counts))))
        ).saveToCouchbase();
        
    }    
}
