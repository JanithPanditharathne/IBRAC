package com.zone24x7.ibrac.eas.processors;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;

import java.io.IOException;

/**
 * Interface to represent the pre-processor.
 */
public interface PreProcessor {

    EventInputParams process(EventInputParams eventInputParams) throws IOException;
}
