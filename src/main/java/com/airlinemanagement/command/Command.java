package com.airlinemanagement.command;

import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public interface Command {

    Map<String, Long> calculateTopRecords(Map<String, List<AirlineDetail>> airlineDataByAirline, Integer Count) throws AirlineManagementException;

    default  Map<String,Long> sortMapDescendingAndReturnLimitedValues(Map<String,Long> map, Integer limit){
        return map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(limit)
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }

}
