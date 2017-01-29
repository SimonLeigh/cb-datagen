package com.couchbase.demo.iot.vehicles;

import com.couchbase.demo.iot.vehicles.model.City;

/**
 * Usefule constants for the data generation
 * 
 * @author david
 */
public interface Constants {
    
     static public final City[] CITIES = {
                        new City("Berlin", 52.53f, 13.4f), 
                        new City("Munich", 48.13f, 11.58f),
                        new City("Hamburg", 53.5f, 9.99f),
                        new City("Cologne", 50.93f, 6.96f),
                        new City("Duesseldorf", 51.22f, 6.77f),
                        new City("Dresden", 51.05f, 13.73f),
                        new City("Bonn", 50.73f, 7.09f)                      
     };
                            
     
     static public final int FUEL_MIN = 10;
     static public final int FUEL_MAX = 90;
     static public final int SPEED_MIN = 20;
     static public final int SPEED_MAX = 80;
     static public final int SPEED_LIMIT = 50;
    
}
