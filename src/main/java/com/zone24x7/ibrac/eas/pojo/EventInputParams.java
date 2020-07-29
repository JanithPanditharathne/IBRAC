package com.zone24x7.ibrac.eas.pojo;

/**
 * Class to represent the Event Input parameters.
 */
public class EventInputParams {
    private String requestId;
    private String topic;
    private String eventData;
    private String contentType;

    /**
     * Constructor to instantiate the EventInputParams.
     *
     * @param requestId   the request id.
     * @param topic       the topic.
     * @param eventData   the event data.
     * @param contentType the content type.
     */
    public EventInputParams(String requestId, String topic, String eventData, String contentType) {
        this.requestId = requestId;
        this.topic = topic;
        this.eventData = eventData;
        this.contentType = contentType;
    }

    /**
     * Method to get the request id.
     *
     * @return request id of the Event Input.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Method to get Topic.
     *
     * @return Topic of the Event Input.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Method to set topic of the event input.
     *
     * @param topic of the event input.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Method to get the event data.
     *
     * @return Event data of the Event Input.
     */
    public String getEventData() {
        return eventData;
    }

    /**
     * Method to set event data of the event input.
     *
     * @param eventData of the event input.
     */
    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    /**
     * Method to get the content type.
     *
     * @return Event type of the Event Input.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Method to set the content type of the event input.
     *
     * @param contentType of the event input.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
