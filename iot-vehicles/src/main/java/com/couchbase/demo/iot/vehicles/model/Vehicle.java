
package com.couchbase.demo.iot.vehicles.model;

import static com.couchbase.demo.iot.vehicles.Constants.*;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Describes a Vehicle
 * 
 * @author david
 */
public class Vehicle {

    public enum vType { CAR, TRUCK, BUS};
    
    private final String id;    
    private final vType type;
    private final int speed;
    private final int fuelLevel;
    private final Route route;
    private City location;
    private final long time;

    /**
     * Default constructor
     */
    public Vehicle() {
    
        //Id
        this.id = UUID.randomUUID().toString();
        
        
        
        //Route
        int idx = randomInt(0, CITIES.length);
        City cityFrom = CITIES[idx];
        idx = randomInt(0, CITIES.length);
        City cityVia = CITIES[idx];
        idx = randomInt(0, CITIES.length);
        City cityTo = CITIES[idx];
        
        this.route = new Route(cityFrom, cityTo, cityVia);
        
        //Location
        idx = randomInt(0, 3);
        City loc = CITIES[0];
        
        if (idx == 0 ) loc = route.getFrom();
        if (idx == 1 ) loc = route.getTo();
        if (idx == 2 ) loc = route.getVia();
        
        this.location = loc;
        
        //Type
        idx = randomInt(0, 3);
        vType t = vType.CAR;;
        if (idx == 1 ) t = vType.TRUCK;
        if (idx == 2 ) t = vType.BUS;
 
        this.type = t;
        
        //Measurements
        this.fuelLevel = randomInt(FUEL_MIN, FUEL_MAX);
        this.speed = randomInt(SPEED_MIN, SPEED_MAX);
        this.time = new Date().getTime();
    }
    
    /**
     * Random number generation
     * 
     * @param min
     * @param max
     * @return 
     */
    private int randomInt(int min, int max) {
        
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public City getLocation() {
        return location;
    }

    public void setLocation(City location) {
        this.location = location;
    }
    

    public int getSpeed() {
        return speed;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public Route getRoute() {
        return route;
    }

    public String getId() {
        return id;
    }

    public vType getType() {
        return type;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"type\" : ").append("\"").append("vehicle").append("\"").append(",");
        sb.append("\"id\" : ").append("\"").append(id).append("\"").append(",");
        sb.append("\"v_type\" : ").append("\"").append(type.toString()).append("\"").append(",");
        sb.append("\"speed\" : ").append(speed).append(",");
        sb.append("\"fuel_level\" : ").append(fuelLevel).append(",");
        sb.append("\"route\" : ").append(route.toString()).append(",");
        sb.append("\"location\" : ").append(location.toString()).append(",");
        sb.append("\"time\" : ").append(time);
        sb.append("}");
       
        return sb.toString();
    } 
}
