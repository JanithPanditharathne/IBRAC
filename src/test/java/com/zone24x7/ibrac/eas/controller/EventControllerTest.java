package com.zone24x7.ibrac.eas.controller;

import com.zone24x7.ibrac.eas.util.TopicValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventControllerTest {
    TopicValidator topicValidator;
    EventController eventController;

    @BeforeEach
    void setup() {
        eventController = new EventController();
        topicValidator = mock(TopicValidator.class);
        List<String> whitelistedTopicList = mock(LinkedList.class);
        whitelistedTopicList.add("rectrack");
        ReflectionTestUtils.setField(topicValidator, "whiteListedTopicList", whitelistedTopicList);
        ReflectionTestUtils.setField(eventController, "topicValidator", topicValidator);
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
    public void should_return_204_no_content_for_valid_post_request_with_valid_parameters() {
        ResponseEntity<Object> responseEntity = eventController.sendTrackingData("rectrack", "text/plain", "request");
        when(topicValidator.validate("rectrack")).thenReturn(true);
        assertThat(responseEntity.getStatusCode().toString(), is("204 No Content"));
        assertThat(responseEntity.getBody().toString(), is(nullValue()));
    }

    @Test
    public void should_return_415_for_invalid_post_request_with_missing_post_body() {
        ResponseEntity<Object> responseEntity = eventController.sendTrackingData("rectrack", "text/plain", null);
        assertThat(responseEntity.getStatusCode().toString(), is("415 Unsupported Media Type"));
        assertThat(responseEntity.getBody().toString(), is(notNullValue()));
    }

    @Test
    public void should_return_404_for_valid_post_request_with_topic_that_does_not_exist() {
        Exception ex = assertThrows(ResponseStatusException.class, () -> eventController.sendTrackingData("topic", "text/plain", "body"));
    }
}