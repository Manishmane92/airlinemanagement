package com.airlinemanagement.parser;

import com.airlinemanagement.application.Application;
import com.airlinemanagement.domain.AirlineDetail;
import com.airlinemanagement.exception.AirlineManagementException;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);

    public List<AirlineDetail> parseFile(String filename) throws AirlineManagementException {
        List<AirlineDetail> beans = null;
        try {
            InputStream stream = Application.class.getResourceAsStream(filename);

            beans = new CsvToBeanBuilder(new InputStreamReader(stream, StandardCharsets.UTF_8))
                    .withType(AirlineDetail.class)
                    .build()
                    .parse();
        }catch (Exception e){
            LOGGER.error("Exception in Parsing file",e.getMessage());
            throw new AirlineManagementException("Exception in Parsing file");
        }
        return beans;
    }
}
