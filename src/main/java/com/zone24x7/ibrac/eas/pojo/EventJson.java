package com.zone24x7.ibrac.eas.pojo;

/**
 * Class to represent the Json sent in the POST request.
 */
public class EventJson {
    private String easTimestamp; // Event Accumulator Service Time Stamp
    private String timeStamp; // Timestamp sent from the frontend
    private String isoTime; // Time in ISO
    private String correlationId; // CorrelationID
    private String sessionId; // sessionID
    private String clientId; // clientID
    private String channel; // channel through which the page was accessed
    private String pageName; // Name of the page in which the request was generated
    private String eventType; // Type of event that triggered the POST request

    /**
     * Method to get the easTimestamp
     *
     * @return easTimestamp of the EventJson
     */
    public String getEasTimestamp() {
        return easTimestamp;
    }

    /**
     * Method to set easTimestamp of the EventJson.
     *
     * @param easTimestamp of the EventJson.
     */
    public void setEasTimestamp(String easTimestamp) {
        this.easTimestamp = easTimestamp;
    }

    /**
     * Method to get the timeStamp
     *
     * @return timeStamp of the EventJson
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Method to set timeStamp of the EventJson.
     *
     * @param timeStamp of the EventJson.
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Method to get the isoTime
     *
     * @return isoTime of the EventJson
     */
    public String getIsoTime() {
        return isoTime;
    }

    /**
     * Method to set isoTime of the EventJson.
     *
     * @param isoTime of the EventJson.
     */
    public void setIsoTime(String isoTime) {
        this.isoTime = isoTime;
    }

    /**
     * Method to get the correlationId
     *
     * @return correlationId of the EventJson
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Method to set correlationId of the EventJson.
     *
     * @param correlationId of the EventJson.
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    /**
     * Method to get the sessionId
     *
     * @return sessionId of the EventJson
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Method to set sessionId of the EventJson.
     *
     * @param sessionId of the EventJson.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Method to get the clientId
     *
     * @return clientId of the EventJson
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Method to set clientId of the EventJson.
     *
     * @param clientId of the EventJson.
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Method to get the channel
     *
     * @return channel of the EventJson
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Method to set channel of the EventJson.
     *
     * @param channel of the EventJson.
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * Method to get the pageName
     *
     * @return pageName of the EventJson
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * Method to set pageName of the EventJson.
     *
     * @param pageName of the EventJson.
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * Method to get the eventType
     *
     * @return eventType of the EventJson
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Method to set eventType of the EventJson.
     *
     * @param eventType of the EventJson.
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
