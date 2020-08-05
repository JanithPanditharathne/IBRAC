package com.zone24x7.ibrac.eas.pojo;

/**
 * Class to represent the Event Json.
 */
public class EventJson {

    private String easTimestamp;
    private String timeStamp;
    private String isoTime;
    private String correlationId;
    private String sessionId;
    private String clientId;
    private String channel;
    private String pageName;
    private String eventType;


    public String getEasTimestamp() {
        return easTimestamp;
    }

    public void setEasTimestamp(String easTimestamp) {
        this.easTimestamp = easTimestamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getIsoTime() {
        return isoTime;
    }

    public void setIsoTime(String isoTime) {
        this.isoTime = isoTime;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
