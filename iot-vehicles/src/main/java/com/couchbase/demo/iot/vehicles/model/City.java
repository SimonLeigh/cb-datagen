
package com.couchbase.demo.iot.vehicles.model;

/**
 * Describes a City
 *  
 * @author david
 */
public class City {
 
    private final String name;
    private final float lat;
    private final float lg;

    public City(String name, float lat, float lg) {
        this.name = name;
        this.lat = lat;
        this.lg = lg;
    }

    public String getName() {
        return name;
    }

    public float getLat() {
        return lat;
    }

    public float getLong() {
        return lg;
    }

    @Override
    public String toString() {
    
        StringBuilder sb = new StringBuilder();
        
        sb.append("{");
        sb.append("\"type\" : ").append("\"").append("city").append("\"").append(",");
        sb.append("\"name\" : ").append("\"").append(this.name).append("\"").append(",");
        sb.append("\"lat\" : " ).append(this.lat).append(",");
        sb.append("\"long\" : " ).append(this.lg); 
        sb.append("}");
         
        return sb.toString();
    }
      
    
}
