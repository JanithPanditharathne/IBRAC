package com.zone24x7.ibrac.eas.publishers;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;

/**
 * Event publisher interface
 */
public interface EventPublisher {

    /**
     * Publish the given message to the given kafka topic.
     *
     * @param eventInputParams     eventInputParams pojo object
     * @return Processed data if everything succeeds, original data if not
     */
    void publishToTopic(EventInputParams eventInputParams);
}
