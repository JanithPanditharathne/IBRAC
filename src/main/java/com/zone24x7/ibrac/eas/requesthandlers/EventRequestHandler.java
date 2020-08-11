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
        // Get the relevant requestConverter
        RequestConverter requestConverter = requestConverterProvider.get(eventInputParams.getTopic());
        // Convert the eventInputParams using the requestConverter
        eventInputParams = requestConverter.convert(eventInputParams);

        // Get the relevant requestFormatter
        RequestFormatter requestFormatter = requestFormatterProvider.get(eventInputParams.getTopic());
        // Format the eventInputParams using the requestFormatter
        eventInputParams = requestFormatter.format(eventInputParams);

        // Get the relevant preProcessor
        PreProcessor preProcessor = preProcessorProvider.get(eventInputParams.getTopic());
        // Process the eventInputParams using the preProcessor (add the date to the eventData)
        eventInputParams = preProcessor.process(eventInputParams);

        // Return the new eventData with the date appended to the beginning.
        return eventInputParams.getEventData();
    }
}
