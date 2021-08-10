package com.airlinemanagement.command;

import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.domain.AirlineRoutes;
import com.airlinemanagement.exception.AirlineManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class RouteCommand implements Command{

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteCommand.class);

    @Override
    public Map<String, Long> calculateTopRecords(Map<String, List<AirlineDetail>> airlineDataByAirline, Integer count) throws AirlineManagementException {
            Map<String, Long> airlinesWithDirectRoutes = new HashMap<>();
            Map<String, Long> sortedRoutes;
            try {
                    for(String airline : airlineDataByAirline.keySet()) {
                        collectDirectRoutesPerAirline(airlineDataByAirline,airlinesWithDirectRoutes, airline);
                    }

                sortedRoutes = sortMapDescendingAndReturnLimitedValues(airlinesWithDirectRoutes,count);
            }catch (Exception e){
                LOGGER.error("Exception in calculate Top Airlines With Maximum Direct Routes",e.getMessage());
                throw new AirlineManagementException("Exception in Exception in calculate Top Airlines With Maximum Direct Routes");
            }
            return sortedRoutes;
    }

    private void collectDirectRoutesPerAirline(Map<String, List<AirlineDetail>> airlineDataByAirline,Map<String, Long> airlinesWithDirectRoutes, String airline) {
        CopyOnWriteArraySet<AirlineRoutes> routes = new CopyOnWriteArraySet<>();
        for(AirlineDetail detail  : airlineDataByAirline.get(airline)){
            AirlineRoutes route = new AirlineRoutes();
            route.setAirline(detail.getAirline());
            route.setSource(detail.getSourceAirport());
            route.setDestination(detail.getDestinationAirport());
            route.setStops(detail.getStops());
            if (!routes.add(route) && !(route.getStops()>0)) {
                if (airlinesWithDirectRoutes.containsKey(detail.getAirline())) {
                    Long cnt = airlinesWithDirectRoutes.get(detail.getAirline());
                    cnt++;
                    airlinesWithDirectRoutes.put(detail.getAirline(), cnt);
                } else {
                    airlinesWithDirectRoutes.put(detail.getAirline(), Long.valueOf(1));
                }
                routes.remove(route);
            }
        }
    }
}
