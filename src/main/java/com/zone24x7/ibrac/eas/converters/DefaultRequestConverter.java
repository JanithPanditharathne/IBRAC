package com.zone24x7.ibrac.eas.converters;

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
     * @param requestBody request body as a string.
     * @return the converted string.
     */
    @Override
    public String convert(String requestBody) {
        return requestBody;
    }
}
