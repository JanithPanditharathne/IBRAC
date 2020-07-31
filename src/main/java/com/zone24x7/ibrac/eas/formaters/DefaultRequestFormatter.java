package com.zone24x7.ibrac.eas.formaters;

import org.springframework.stereotype.Component;

/**
 * Default RequestFormatter
 */
@Component
public class DefaultRequestFormatter implements RequestFormatter {

    /**
     * Method to format the request body.
     *
     * @param requestBody request body as a string.
     * @return formatted request body as a string.
     */
    @Override
    public String format(String requestBody) {
        return requestBody;
    }
}
