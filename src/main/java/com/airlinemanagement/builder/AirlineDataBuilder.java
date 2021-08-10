package com.airlinemanagement.builder;

import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AirlineDataBuilder {

    private static AirlineDataBuilder builder = null;

    private  Map<String, List<AirlineDetail>> airlineDataByAirline = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineDataBuilder.class);

    private AirlineDataBuilder(){

    }

    public static synchronized AirlineDataBuilder getInstance(){
        if(builder == null){
            builder = new AirlineDataBuilder();
        }
        return builder;
    }

    public Map<String, List<AirlineDetail>> initialize(List<AirlineDetail> data) throws AirlineManagementException {
        airlineDataByAirline = new ConcurrentHashMap<>();
        try {
            for (AirlineDetail detail : data) {
                if (airlineDataByAirline.containsKey(detail.getAirline())) {
                    airlineDataByAirline.get(detail.getAirline()).add(detail);
                } else {
                    List<AirlineDetail> details = new ArrayList<>();
                    details.add(detail);
                    airlineDataByAirline.put(detail.getAirline(), details);
                }
            }
        }catch (Exception e){
            LOGGER.error("Exception in initializing data builder",e.getMessage());
            throw new AirlineManagementException("Exception in initializing data builder");

        }
        return airlineDataByAirline;
    }

}
