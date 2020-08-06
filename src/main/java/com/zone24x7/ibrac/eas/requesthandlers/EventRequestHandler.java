package com.zone24x7.ibrac.eas.requesthandlers;

import com.zone24x7.ibrac.eas.converters.RequestConverter;
import com.zone24x7.ibrac.eas.converters.RequestConverterProvider;
import com.zone24x7.ibrac.eas.formaters.RequestFormatter;
import com.zone24x7.ibrac.eas.formaters.RequestFormatterProvider;
import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.processors.PreProcessor;
import com.zone24x7.ibrac.eas.processors.PreProcessorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Default EventRequestHandler
 */
@Component
public class EventRequestHandler implements RequestHandler {
    @Autowired
    private RequestConverterProvider requestConverterProvider;

    @Autowired
    private PreProcessorProvider preProcessorProvider;

    @Autowired
    private RequestFormatterProvider requestFormatterProvider;

    /**
     * Method to convert, process and format the request body.
     *
     * @param eventInputParams Input parameters object sent from the controller class
     */
    @Override
    public String handleRequest( EventInputParams eventInputParams) throws IOException {
        RequestConverter requestConverter = requestConverterProvider.get(eventInputParams.getTopic());
        eventInputParams = requestConverter.convert(eventInputParams);

        RequestFormatter requestFormatter = requestFormatterProvider.get(eventInputParams.getTopic());
        eventInputParams = requestFormatter.format(eventInputParams);

        PreProcessor preProcessor = preProcessorProvider.get(eventInputParams.getTopic());
        eventInputParams = preProcessor.process(eventInputParams);

        return eventInputParams.getEventData();
    }
}
