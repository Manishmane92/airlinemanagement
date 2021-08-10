package com.airlinemanagement.domain;

import java.util.Objects;

public class AirlineRoutes {

    private String airline;

    private String source;

    private String destination;

    private Integer stops;


    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getStops() {
        return stops;
    }

    public void setStops(Integer stops) {
        this.stops = stops;
    }

    @Override
    public boolean equals(Object o) {
        AirlineRoutes route = (AirlineRoutes) o;
        return this.airline.equals(route.getAirline()) &&
                this.source.equals(route.getDestination())
                && this.destination.equals(route.getSource())
                && this.stops.equals(0) && route.stops.equals(0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airline, source, destination, stops);
    }
}
