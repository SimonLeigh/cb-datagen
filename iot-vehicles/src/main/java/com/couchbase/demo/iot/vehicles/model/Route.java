package com.couchbase.demo.iot.vehicles.model;


/**
 * Describes a simple route
 * 
 * @author david
 */
public class Route {
    
    private final City from;
    private final City to;
    private final City via;

    public Route(City from, City to, City via) {
        this.from = from;
        this.to = to;
        this.via = via;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public City getVia() {
        return via;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"type\" : ").append("\"").append("route").append("\"").append(",");
        sb.append("\"from\" : ").append(from.toString()).append(",");
        sb.append("\"to\" : ").append(to.toString()).append(",");
        sb.append("\"via\" : ").append(via.toString());
        sb.append("}");
        
        return sb.toString();
    }
           
    
}
