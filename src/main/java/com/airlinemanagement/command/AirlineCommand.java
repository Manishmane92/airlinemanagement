package com.airlinemanagement.command;

import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class AirlineCommand implements Command{

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineCommand.class);

    @Override
    public Map<String, Long> calculateTopRecords(Map<String, List<AirlineDetail>> airlineDataByAirline, Integer count) throws AirlineManagementException {
        Map<String, Long> visitedCities;
        Map<String, Long> sortedVisitedCities;
        try {
            visitedCities = mapAirlineToVisitedCities(airlineDataByAirline);

            sortedVisitedCities = sortMapDescendingAndReturnLimitedValues(visitedCities,count);

        }catch (Exception e){
            LOGGER.error("Exception in calculate Top Airline Covering Max Cities ",e.getMessage());
            throw new AirlineManagementException("Exception in calculate Top Airline Covering Max Cities");
        }
        return sortedVisitedCities;
    }

    private Map<String, Long> mapAirlineToVisitedCities(Map<String, List<AirlineDetail>> airlineDataByAirline) {
        Map<String, Long> visitedCities = new HashMap<>();
        for(Map.Entry<String,List<AirlineDetail>> airline : airlineDataByAirline.entrySet()) {
        Set<String> cities = new HashSet<>();
        List<AirlineDetail> detailByAirline = airline.getValue();
        /*.filter(s -> !"Y".equals(s.getCodeShare()))*/
        cities.addAll(detailByAirline.parallelStream().map(AirlineDetail :: getSourceAirport).collect(Collectors.toSet()));
        cities.addAll(detailByAirline.parallelStream().map(AirlineDetail :: getDestinationAirport).collect(Collectors.toSet()));
        visitedCities.put(airline.getKey(), cities.stream().count());
        }
        return visitedCities;
    }
}
