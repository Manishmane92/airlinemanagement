package com.airlinemanagement.command;

import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CityCommand implements Command{

    private static final Logger LOGGER = LoggerFactory.getLogger(CityCommand.class);
    @Override
    public Map<String, Long> calculateTopRecords(Map<String, List<AirlineDetail>> airlineDataByAirline, Integer count) throws AirlineManagementException {
            Map<String, Long> citiesWithAirlineSortedOrder;
            try {
                Map<String, Long> citiesWithAirlineCount = mapServingAirlinesCountPerCity(airlineDataByAirline);

                citiesWithAirlineSortedOrder = sortMapDescendingAndReturnLimitedValues(citiesWithAirlineCount, count);

            }catch (Exception e){
                LOGGER.error("Exception in calculate Top Cities With Max Airlines",e.getMessage());
                throw new AirlineManagementException("Exception in calculate Top Cities With Max Airlines");
            }

            return citiesWithAirlineSortedOrder;
    }

    private Map<String, Long> mapServingAirlinesCountPerCity(Map<String, List<AirlineDetail>> airlineDataByAirline) {
        Map<String, Set<String>> citiesWithAirlines = collectAirlinesPerCity(airlineDataByAirline);
        Map<String, Long> citiesWithAirlineCount = new HashMap<>();
        citiesWithAirlines.keySet().forEach(a ->
            citiesWithAirlineCount.put(a, Long.valueOf(citiesWithAirlines.get(a).size())));
        return citiesWithAirlineCount;
    }

    private Map<String, Set<String>> collectAirlinesPerCity(Map<String, List<AirlineDetail>> airlineDataByAirline) {
        Map<String, Set<String>> citiesWithAirlines = new HashMap<>();
        for(Map.Entry<String,List<AirlineDetail>> airline : airlineDataByAirline.entrySet()) {
            for(AirlineDetail detail : airline.getValue()) {
                if (citiesWithAirlines.containsKey(detail.getSourceAirport())) {
                    citiesWithAirlines.get(detail.getSourceAirport()).add(airline.getKey());
                } else {
                    HashSet<String> airlines = new HashSet<>();
                    airlines.add(airline.getKey());
                    citiesWithAirlines.put(detail.getSourceAirport(), airlines);
                }

                if (citiesWithAirlines.containsKey(detail.getDestinationAirport())) {
                    citiesWithAirlines.get(detail.getDestinationAirport()).add(airline.getKey());
                } else {
                    HashSet<String> airlines = new HashSet<>();
                    airlines.add(airline.getKey());
                    citiesWithAirlines.put(detail.getDestinationAirport(), airlines);
                }
            }

        }
        return citiesWithAirlines;
    }
}
