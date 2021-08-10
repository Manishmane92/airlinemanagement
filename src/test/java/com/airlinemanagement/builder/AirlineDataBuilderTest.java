package com.airlinemanagement.builder;

import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;
import com.airlinemanagement.parser.CsvParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class AirlineDataBuilderTest {


    AirlineDataBuilder builder;

    List<AirlineDetail> details;

    @Before
    public void setUp() throws AirlineManagementException {
        details  = new CsvParser().parseFile("/airline-routes-test.dat");
        builder = AirlineDataBuilder.getInstance();
    }

    @Test
    public void shouldReturnInitData() throws AirlineManagementException {
        Assert.assertNotNull(builder.initialize(details));
    }

    @Test(expected = AirlineManagementException.class)
    public void shouldReturnExceptionWhenPassingNull() throws AirlineManagementException {
        builder.initialize(null);
    }






}
