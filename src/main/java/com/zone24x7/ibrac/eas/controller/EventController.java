package com.zone24x7.ibrac.eas.controller;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.publishers.KafkaEventPublisher;
import com.zone24x7.ibrac.eas.requesthandlers.EventRequestHandler;
import com.zone24x7.ibrac.eas.util.StringConstants;
import com.zone24x7.ibrac.eas.util.TopicValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

/**
 * Event Controller class for publishing tracking events
 */
@RestController
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private TopicValidator topicValidator;

    @Autowired
    private EventRequestHandler eventRequestHandler;

    @Autowired
    private KafkaEventPublisher kafkaEventPublisher;

    /**
     * Method to get a Unique Correlation ID.
     *
     * @return unique correlation id
     */
    @GetMapping(path = "/eas/v1/correlation/id", produces = "text/plain")
    public ResponseEntity<Object> getCorrelationId() {
        // Generate random UUID and include the same into the response.
        return ResponseEntity.ok().body(UUID.randomUUID().toString());
    }

    /**
     * Method to send tracking data into the messaging platform
     *
     * @return HTTP Status 204 no content if the request is submitted. HTTP status 415 if the POST body is missing. HTTP status 404 if the topic is not found.
     */
    @PostMapping(path = "/eas/v1/topics/{topic}", consumes = {"application/json", "text/plain"})
    public ResponseEntity<Object> sendTrackingData(@PathVariable("topic") String topic, @RequestHeader("Content-type") String contentType, @RequestBody String requestBody) {
        //Get a new correlationId and store it in requestId
        String requestId = MDC.get("correlationId");
        EventInputParams eventInputParams = new EventInputParams(requestId, topic, requestBody, contentType);

        try {
            if (topicValidator.validate(topic)) {
                //Call to eventRequestHandler, to convert, format and process the eventData
                eventRequestHandler.handleRequest(eventInputParams);
                //Call to kafkaEventPublisher, to publish the message to the topic.
                kafkaEventPublisher.publishToTopic(eventInputParams);
                // Upon Successful calls, return 204 no content response
                return ResponseEntity.noContent().build();
            } else { // If topic is not present in the properties file
                // Log the topic that was sent in the POST
                LOGGER.error(StringConstants.REQUEST_ID_LOG_MSG_PREFIX + "Topic not supported: {}", requestId, topic);
                // Return error code 404 and error message - "Error, Topic Not Found"
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, Topic Not Found");
            }
        } catch (IOException ioException) { // If an invalid JSON request was passed, handleRequest() will throw an IOException which will be caught here.
            // Log the requestId and eventData that was sent in the POST
            LOGGER.error(StringConstants.REQUEST_ID_LOG_MSG_PREFIX + "Json parsing error: {}", requestId, eventInputParams.getEventData());
            // Return error code 505 and error message - "Error, Json parsing error"
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error, Json parsing error");
        }
    }
}
