package com.zone24x7.ibrac.eas.publishers;

/**
 * Publisher interface
 */
public interface EventPublisher {

    /**
     * Publish the given message to the given kafka topic.
     *
     * @param topicName     name of the topic to be published
     * @param data          message to publish
     * @param requestId     request Id
     * @return Processed data if everything succeeds, original data if not
     */
    void publishToTopic(String topicName, String data, String requestId);
}
