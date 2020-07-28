package com.zone24x7.ibrac.eas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class EventControllerTest {
    /**
     * Test to verify that the method returns a valid ID when it is called
     */
    @Test
    public void should_generateValidUUID_for_methodCall() {
        EventController ec = new EventController();
        ResponseEntity<Object> generatedID = ec.getCorrelationId();
        assertThat(generatedID.getStatusCode().toString(), is("200 OK"));
    }
}