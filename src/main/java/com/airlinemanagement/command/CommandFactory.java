package com.airlinemanagement.command;

import com.airlinemanagement.domain.CommandType;
import com.airlinemanagement.exception.AirlineManagementException;

public class CommandFactory {

    private CommandFactory(){

    }

    public static Command getInstance(CommandType type) throws AirlineManagementException {
        Command command = null;
        switch (type){
            case AIRLINE:
                command =  new AirlineCommand();
                break;
            case ROUTE:
                command = new RouteCommand();
                break;
            case CITY:
                command = new CityCommand();
                break;
            default:
                  throw new AirlineManagementException("Invalid Command Type");
        }
        return command;
    }
}
