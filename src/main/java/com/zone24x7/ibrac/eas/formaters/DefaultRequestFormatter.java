package com.zone24x7.ibrac.eas.formaters;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import org.springframework.stereotype.Component;

/**
 * Default RequestFormatter
 */
@Component
public class DefaultRequestFormatter implements RequestFormatter {

    /**
     * Method to format the request body.
     *
     * @param eventInputParams pojo object of eventInputParams..
     * @return EventInputParams params object with the changes.
     */
    @Override
    public EventInputParams format(EventInputParams eventInputParams) {
        return eventInputParams;
    }
}
