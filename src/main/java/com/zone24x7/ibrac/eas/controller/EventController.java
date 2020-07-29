package com.zone24x7.ibrac.eas.controller;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Event Controller class for publishing tracking events
 */
@RestController
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    /**
     * Method to get a Unique Correlation ID.
     *
     * @return unique correlation id
     */
    @GetMapping(path = "/eas/v1/correlation/id", produces = "text/plain")
    public ResponseEntity<Object> getCorrelationId() {
        return ResponseEntity.ok().body(UUID.randomUUID().toString());
    }

    /**
     * Method to send tracking data into the messaging platform
     *
     * @return HTTP Status 204 no content if the request is submitted. HTTP status 415 if the POST body is missing.
     */
    @PostMapping(path = "/eas/v1/topics/{topic}", consumes = {"application/json", "text/plain"})
    public ResponseEntity<Object> sendTrackingData(@PathVariable("topic") String topic, @RequestHeader("content-type") String contentType, @RequestBody String requestBody) {
        String requestId = MDC.get("correlationId");
        EventInputParams eventInputParams = new EventInputParams(requestId, topic, requestBody, contentType);
        return ResponseEntity.noContent().build();
    }
}
