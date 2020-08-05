package com.zone24x7.ibrac.eas.requesthandlers;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

class EventRequestHandlerTest {
    EventRequestHandler eventRequestHandler;

    @BeforeEach
    void setup() {
        eventRequestHandler = mock(EventRequestHandler.class);
    }

    /**
     * Test to verify that the topic validator method returns true for an existing topic.
     */
    @Test
    public void should_return_the_same_string_for_rectrack_topic() {
//        String requestId = MDC.get("correlationId");
//        //String output = eventRequestHandler.handleRequest(new EventInputParams(requestId, "rectrack", "data", "String"));
//        assertThat(output, is("data"));
    }

}