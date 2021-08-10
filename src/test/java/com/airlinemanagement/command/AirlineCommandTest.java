package com.airlinemanagement.command;

import com.airlinemanagement.builder.AirlineDataBuilder;
import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;
import com.airlinemanagement.parser.CsvParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirlineCommandTest {

    Map<String, List<AirlineDetail>> airlineData;

    Command command = null;

    @Before
    public void setUp() throws AirlineManagementException {
        List<AirlineDetail> details  = new CsvParser().parseFile("/airline-routes-test.dat");
        AirlineDataBuilder builder = AirlineDataBuilder.getInstance();
        airlineData = builder.initialize(details);
        command = new AirlineCommand();
    }

    @Test
    public void shouldReturnTopThreeAirlineCoveringMaxCities() throws AirlineManagementException {
        Map<String, Long> topThreeVisitedCities = command.calculateTopRecords(airlineData,3);

        Assert.assertEquals(3 ,topThreeVisitedCities.keySet().size());
        ArrayList<Long> topCities = new ArrayList<>(topThreeVisitedCities.values());
        Assert.assertEquals(Long.valueOf(13),topCities.get(0));
        Assert.assertEquals(Long.valueOf(11),topCities.get(1));
        Assert.assertEquals(Long.valueOf(10),topCities.get(2));

    }
}
