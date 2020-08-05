package com.zone24x7.ibrac.eas.formaters;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;

/**
 * RequestFormatter interface
 */
public interface RequestFormatter {

    /**
     * Method to format the request body.
     *
     * @param eventInputParams pojo object of eventInputParams..
     * @return EventInputParams params object with the changes.
     */
    EventInputParams format(EventInputParams eventInputParams);
}
