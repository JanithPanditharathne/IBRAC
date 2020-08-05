package com.zone24x7.ibrac.eas.requesthandlers;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;

import java.io.IOException;

/**
 * RequestHandler interface
 */
public interface RequestHandler {

    /**
     * Method to convert, process and format the request body.
     *
     * @param eventInputParams Input parameters object sent from the controller class
     */
    public String handleRequest(EventInputParams eventInputParams) throws IOException;
}
