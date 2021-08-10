package com.airlinemanagement.application;

import com.airlinemanagement.builder.AirlineDataBuilder;
import com.airlinemanagement.command.CommandFactory;
import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.domain.CommandType;
import com.airlinemanagement.exception.AirlineManagementException;
import com.airlinemanagement.parser.CsvParser;
import java.util.*;

public class Application {

    public static void main(String ar[]) throws AirlineManagementException {

        List<AirlineDetail> details  = new CsvParser().parseFile("/airline-routes.dat");

        AirlineDataBuilder builder = AirlineDataBuilder.getInstance();
        Map<String, List<AirlineDetail>> airlineData = builder.initialize(details);


        Map<String, Long> topThreeVisitedCities = CommandFactory.getInstance(CommandType.AIRLINE).calculateTopRecords(airlineData,3);
        System.out.println("================ TOP 3 Airlines which cover maximum cities ===============");
        topThreeVisitedCities.entrySet().forEach(entry ->
                System.out.println("Airline :" + entry.getKey() + "    Number of Covered cities : " + entry.getValue()));

        Map<String, Long> topThreeRoutes = CommandFactory.getInstance(CommandType.ROUTE).calculateTopRecords(airlineData,3);
        System.out.println("================ TOP 3 Airlines which has maximum direct routes ===============");
        topThreeRoutes.entrySet().forEach(entry ->
            System.out.println("Airline :" + entry.getKey() + "    Number of of Direct routes : " + entry.getValue()));


        Map<String, Long> topTenCities = CommandFactory.getInstance(CommandType.CITY).calculateTopRecords(airlineData,10);
        System.out.println("================ TOP 10 Cities which has maximum airlines serving them ===============");
        topTenCities.entrySet().forEach(entry ->
            System.out.println("City :" + entry.getKey() + "    Number of Serving Airlines : " + entry.getValue()));

    }

}
