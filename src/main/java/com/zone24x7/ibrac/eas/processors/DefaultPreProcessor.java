package com.zone24x7.ibrac.eas.processors;

import org.springframework.stereotype.Component;

/**
 * Implementation class of the pre processor to represent the default pre processor.
 */
@Component
public class DefaultPreProcessor implements PreProcessor {

    /**
     * Method to process the request body.
     *
     * @param requestBody request body as a string.
     * @return processed request body as a string.
     */
    @Override
    public String process(String requestBody) {
        return requestBody;
    }
}
