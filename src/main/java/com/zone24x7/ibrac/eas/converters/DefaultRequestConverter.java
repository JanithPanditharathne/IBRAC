package com.zone24x7.ibrac.eas.converters;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.util.StringConstants;
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
     * @return the converted string.
     */
    @Override
    public EventInputParams convert(EventInputParams eventInputParams) {
        return eventInputParams;
    }
}
