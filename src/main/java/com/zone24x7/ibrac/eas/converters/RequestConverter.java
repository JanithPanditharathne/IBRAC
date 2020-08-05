package com.zone24x7.ibrac.eas.converters;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;

/**
 * Interface to represent the request converter.
 */
public interface RequestConverter {

    /**
     * Method to convert the request body.
     *
     * @param eventInputParams pojo object of eventInputParams.
     * @return the converted string.
     */
    EventInputParams convert(EventInputParams eventInputParams);

}
