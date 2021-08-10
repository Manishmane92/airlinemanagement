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

public class RouteCommandTest {

    Map<String, List<AirlineDetail>> airlineData;

    Command command = null;

    @Before
    public void setUp() throws AirlineManagementException {
        List<AirlineDetail> details  = new CsvParser().parseFile("/airline-routes-test.dat");
        AirlineDataBuilder builder = AirlineDataBuilder.getInstance();
        airlineData = builder.initialize(details);
        command = new RouteCommand();
    }

    @Test
    public void shouldReturnTopThreeDirectRoutes() throws AirlineManagementException {
        Map<String, Long> topThreeDirectRoutes = command.calculateTopRecords(airlineData,3);;

        Assert.assertEquals(3,topThreeDirectRoutes.keySet().size());
        ArrayList<Long> topRoutes = new ArrayList<>(topThreeDirectRoutes.values());
        Assert.assertEquals(Long.valueOf(3),topRoutes.get(0));
        Assert.assertEquals(Long.valueOf(1),topRoutes.get(1));
        Assert.assertEquals(Long.valueOf(1),topRoutes.get(2));

    }
}
