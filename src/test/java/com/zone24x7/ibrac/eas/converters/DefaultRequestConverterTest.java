package com.zone24x7.ibrac.eas.converters;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class DefaultRequestConverterTest {
    /**
     * Method to test that the same object is returned.
     */
    @Test
    void should_return_same_object_that_was_sent_as_a_parameter() {
        String requestId = MDC.get("correlationId");
        EventInputParams eventInputParams = new EventInputParams(requestId, "test", "eventData", "contentType");
        DefaultRequestConverter defaultRequestConverter = new DefaultRequestConverter();
        EventInputParams returnedEventInputParams = defaultRequestConverter.convert(eventInputParams);
        // AssertThat the original eventInputParams is the same as the returned event input params
        assertThat(eventInputParams, is(returnedEventInputParams));
    }

    /**
     * Method to test that null is sent when a null object is sent as the parameter.
     */
    @Test
    void should_return_null_object_when_null_object_is_sent_as_the_parameter() {
        EventInputParams eventInputParams = null;
        DefaultRequestConverter defaultRequestConverter = new DefaultRequestConverter();
        EventInputParams returnedEventInputParams = defaultRequestConverter.convert(eventInputParams);
        // AssertThat null is returned when null is passed to the method
        assertThat(returnedEventInputParams, is(nullValue()));
    }

}