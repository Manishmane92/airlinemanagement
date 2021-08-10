package com.airlinemanagement.parser;

import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CsvParserTest {

    @Test
    public void shouldParseObjectFromFile() throws AirlineManagementException{
        List<AirlineDetail> details = new CsvParser().parseFile("/airline-routes-test.dat");
        Assert.assertNotNull(details);
    }

    @Test(expected = AirlineManagementException.class)
    public void shouldThrowExceptionWhenWrongFilenameIsGiven() throws AirlineManagementException{
        List<AirlineDetail> details = new CsvParser().parseFile("/airline-test.dat");
    }
}
