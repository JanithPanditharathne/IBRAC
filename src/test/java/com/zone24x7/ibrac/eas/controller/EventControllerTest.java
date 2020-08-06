package com.zone24x7.ibrac.eas.controller;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.publishers.KafkaEventPublisher;
import com.zone24x7.ibrac.eas.requesthandlers.EventRequestHandler;
import com.zone24x7.ibrac.eas.util.CustomReflectionTestUtils;
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
import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test the Event Controller.
 */
public class EventControllerTest {
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
    void setup() throws Exception {
        eventController = new EventController();
        eventInputParams = new EventInputParams(requestId,topicName, request,StringConstants.TEXT_PLAIN);

        topicValidator = mock(TopicValidator.class);
        eventRequestHandler = mock(EventRequestHandler.class);
        kafkaEventPublisher = mock(KafkaEventPublisher.class);
        logger = mock(Logger.class);

        Field loggerField = eventController.getClass().getDeclaredField("LOGGER");
        CustomReflectionTestUtils.setFinalStaticField(loggerField, this.logger);

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
    public void should_generateValidUUID_for_methodCall() {
        ResponseEntity<Object> responseEntity = eventController.getCorrelationId();
        assertThat(responseEntity.getStatusCode().toString(), is("200 OK"));
        assertThat(responseEntity.getBody().toString(), is(notNullValue()));
    }

    @Test
    public void should_return_204_no_content_for_valid_post_request_with_valid_parameters() throws IOException {
        when(topicValidator.validate(topicName)).thenReturn(true);
        when(mdcAdapter.get("correlationId")).thenReturn(requestId);
        when(eventRequestHandler.handleRequest(eventInputParams)).thenReturn(null);
        ResponseEntity<Object> responseEntity = eventController.sendTrackingData(topicName, StringConstants.TEXT_PLAIN, request);
        assertThat(responseEntity.getStatusCode().getReasonPhrase().toString(), is("No Content"));
        assertThat(responseEntity.getBody(), is(nullValue()));
    }

    @Test
    public void should_return_500_internal_server_error_when_invalid_json_request() throws IOException {
        when(topicValidator.validate(topicName)).thenReturn(true);
        when(mdcAdapter.get("correlationId")).thenReturn(requestId);
        when(eventRequestHandler.handleRequest(ArgumentMatchers.any())).thenThrow(new IOException());
        assertThrows(ResponseStatusException.class, () -> eventController.sendTrackingData(topicName, StringConstants.TEXT_PLAIN, request));
    }

    @Test
    public void should_return_415_for_invalid_post_request_with_invalid_content_type() {
        assertThrows(ResponseStatusException.class, () -> eventController.sendTrackingData(topicName, "xml", request));
    }

    @Test
    public void should_return_404_for_valid_post_request_with_topic_that_does_not_exist() {
        assertThrows(ResponseStatusException.class, () -> eventController.sendTrackingData(topicName, StringConstants.TEXT_PLAIN, request));
    }
}