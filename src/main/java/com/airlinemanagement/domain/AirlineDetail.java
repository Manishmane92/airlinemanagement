package com.airlinemanagement.domain;

public class AirlineDetail {

    private static String nullValue = "N";

    private String airline;

    private String airlineId;

    private String sourceAirport;

    private String sourceAirportId;

    private String destinationAirport;

    private String destinationAirportId;

    private String codeShare;

    private Integer stops;

    private String equipment;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        if(airline.equals(nullValue)) {
            this.airline = null;
        }else {
            this.airline = airline;
        }
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        if(airlineId.equals(nullValue)) {
            this.airlineId = null;
        }else {
            this.airlineId = airlineId;
        }
    }

    public String getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(String sourceAirport) {
        if(sourceAirport.equals(nullValue)) {
            this.sourceAirport = null;
        }else {
            this.sourceAirport = sourceAirport;
        }
    }

    public String getSourceAirportId() {
        return sourceAirportId;
    }

    public void setSourceAirportId(String sourceAirportId) {
        if(sourceAirportId.equals(nullValue)){
            this.sourceAirportId = null;
        }else {
            this.sourceAirportId = sourceAirportId;
        }
    }

    public String getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(String destinationAirportId) {
        if(destinationAirportId.equals(nullValue)) {
            this.destinationAirportId = null;
        }else {
            this.destinationAirportId = destinationAirportId;
        }
    }

    public String getCodeShare() {
        return codeShare;
    }

    public void setCodeShare(String codeShare) {
        if(codeShare.equals(nullValue)) {
            this.codeShare = null;
        }else {
            this.codeShare = codeShare;
        }
    }

    public Integer getStops() {
        return stops;
    }

    public void setStops(Integer stops) {
        this.stops = stops;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        if(equipment.equals(nullValue)) {
            this.equipment = null;
        }else {
            this.equipment = equipment;
        }
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        if(destinationAirport.equals(nullValue)) {
            this.destinationAirport = null;
        }else {
            this.destinationAirport = destinationAirport;
        }
    }
}
