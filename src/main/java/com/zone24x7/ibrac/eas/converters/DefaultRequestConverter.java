package com.zone24x7.ibrac.eas.converters;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Implementation class to represent the default RequestConverter
 */
@Component
@Qualifier("defaultRequestConverter")
public class DefaultRequestConverter implements RequestConverter {

    /**
     * Method to convert the request body.
     *
     * @param eventInputParams pojo object of eventInputParams.
     * @return eventInputParams with the converted string.
     */
    @Override
    public EventInputParams convert(EventInputParams eventInputParams) {
        // Return the eventInputParams with the converted string
        return eventInputParams;
    }
}
