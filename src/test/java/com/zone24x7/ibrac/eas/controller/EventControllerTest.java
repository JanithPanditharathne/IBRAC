package com.zone24x7.ibrac.eas.controller;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.publishers.KafkaEventPublisher;
import com.zone24x7.ibrac.eas.requesthandlers.EventRequestHandler;
import com.zone24x7.ibrac.eas.util.StringConstants;
import com.zone24x7.ibrac.eas.util.TopicValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test the Event Controller.
 */
class EventControllerTest {
    private TopicValidator topicValidator;
    private EventController eventController;
    private EventRequestHandler eventRequestHandler;
    private MDCAdapter mdcAdapter;
    private EventInputParams eventInputParams;
    private KafkaEventPublisher kafkaEventPublisher;
    private Logger logger;

    private String topicName = "rectrack";
    private String request = "request";
    private String requestId = "12345";


    /**
     * Method to setup the dependencies for the test class
     */
    @BeforeEach
    void setup() {
        eventController = new EventController();
        eventInputParams = new EventInputParams(requestId,topicName, request,StringConstants.TEXT_PLAIN);

        topicValidator = mock(TopicValidator.class);
        eventRequestHandler = mock(EventRequestHandler.class);
        kafkaEventPublisher = mock(KafkaEventPublisher.class);
        logger = mock(Logger.class);

        ReflectionTestUtils.setField(eventController, "topicValidator", topicValidator);
        ReflectionTestUtils.setField(eventController, "eventRequestHandler", eventRequestHandler);
        ReflectionTestUtils.setField(eventController, "kafkaEventPublisher", kafkaEventPublisher);

        mdcAdapter = mock(MDCAdapter.class);
        ReflectionTestUtils.setField(MDC.class, "mdcAdapter", mdcAdapter);
    }

    /**
     * Test to verify that the method returns a valid ID when it is called
     */
    @Test
    void should_generateValidUUID_for_methodCall() {
        // Create a new responseEntity
        ResponseEntity<Object> responseEntity = eventController.getCorrelationId();
        // AssertThat the status code is 200 OK
        assertThat(responseEntity.getStatusCode().toString(), is("200 OK"));
        // AssertThat the response body is not null
        assertThat(responseEntity.getBody(), is(notNullValue()));
    }

    /**
     * Test to verify that the method returns 204 no content on valid post request
     */
    @Test
    void should_return_204_no_content_for_valid_post_request_with_valid_parameters() throws IOException {
        when(topicValidator.validate(topicName)).thenReturn(true);
        when(mdcAdapter.get("correlationId")).thenReturn(requestId);
        when(eventRequestHandler.handleRequest(eventInputParams)).thenReturn(null);
        ResponseEntity<Object> responseEntity = eventController.sendTrackingData(topicName, StringConstants.TEXT_PLAIN, request);
        // AssertThat the status code returned is No Content
        assertThat(responseEntity.getStatusCode().getReasonPhrase(), is("No Content"));
        // AssertThat the response body is null
        assertThat(responseEntity.getBody(), is(nullValue()));
    }

    /**
     * Test to verify that the method returns 500 internal server error when invalid json is passed as the request body
     */
    @Test
    void should_return_500_internal_server_error_when_invalid_json_request() throws IOException {
        when(topicValidator.validate(topicName)).thenReturn(true);
        when(mdcAdapter.get("correlationId")).thenReturn(requestId);
        when(eventRequestHandler.handleRequest(ArgumentMatchers.any())).thenThrow(new IOException());
        // AssertThat a ResponseStatusException is thrown if an invalid json is passed in the request body.
        assertThrows(ResponseStatusException.class, () -> eventController.sendTrackingData(topicName, StringConstants.TEXT_PLAIN, request));
    }

    /**
     * Test to verify that the method returns 415 invalid post request when Invalid content type passed
     */
    @Test
    void should_return_415_for_invalid_post_request_with_invalid_content_type() {
        // AssertThat a ResponseStatusException is thrown if a content type that is not supported is passed.
        assertThrows(ResponseStatusException.class, () -> eventController.sendTrackingData(topicName, "xml", request));
    }

    /**
     * Test to verify that the method returns 404 on invalid topic name passed
     */
    @Test
    void should_return_404_for_valid_post_request_with_topic_that_does_not_exist() {
        // AssertThat a ResponseStatusException is thrown when a topic that does not exist is passed.
        assertThrows(ResponseStatusException.class, () -> eventController.sendTrackingData(topicName, StringConstants.TEXT_PLAIN, request));
    }
}