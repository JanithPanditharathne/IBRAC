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
    public String method(EventInputParams eventInputParams) {
        RequestConverter requestConverter = requestConverterProvider.get(eventInputParams.getTopic());
        String convertedString = requestConverter.convert(eventInputParams.getEventData());

        RequestFormatter requestFormatter = requestFormatterProvider.get(eventInputParams.getTopic());
        String formattedString = requestFormatter.format(convertedString);

        PreProcessor preProcessor = preProcessorProvider.get(eventInputParams.getTopic());
        String processedString = preProcessor.process(formattedString);

        return processedString;
    }
}
