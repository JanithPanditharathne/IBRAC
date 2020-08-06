package com.zone24x7.ibrac.eas.processors;

import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.junit.Test;
import org.slf4j.MDC;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class DefaultPreProcessorTest {
    /**
     * Method to test that the object with date appended to it is passed when a valid object as text is passed.
     */
    @Test
    public void should_return_object_with_time_appended_to_the_event_data_when_valid_object_as_text_is_passed() {
        String requestId = MDC.get("correlationId");
        EventInputParams eventInputParams = new EventInputParams(requestId, "test", "eventData", StringConstants.TEXT_PLAIN);
        DefaultPreProcessor defaultPreProcessor = new DefaultPreProcessor();
        EventInputParams returnedEventInputParams = null;
        try {
            returnedEventInputParams = defaultPreProcessor.process(eventInputParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(returnedEventInputParams, is(notNullValue()));
        assertThat(eventInputParams, is(returnedEventInputParams));
    }

    /**
     * Method to test that the object with date appended to it is passed when a valid object as json is passed.
     */
    @Test
    public void should_return_object_with_time_appended_to_the_event_data_when_valid_object_as_json_is_passed() {
        String requestId = MDC.get("correlationId");
        String jsonInputString = "{\n" +
                "  \"timeStamp\": 1467113349463," +
                "  \"isoTime\": \"2016-10-05T06:28:00.997Z\"," +
                "  \"correlationId\": \"eaf4df48-0e00-1ab9-1ffe-8de877450a99\"," +
                "  \"sessionId\": \"fdf4df48-7e00-1569-115e-8de877450a47\"," +
                "  \"clientId\": \"2253998837150436\"," +
                "  \"channel\": \"Demo\"," +
                "  \"pageName\": \"PDP\"," +
                "  \"eventType\": \"user_login\"" +
                "}";

        EventInputParams eventInputParams = new EventInputParams(requestId, "test", jsonInputString, StringConstants.APPLICATION_JSON);
        DefaultPreProcessor defaultPreProcessor = new DefaultPreProcessor();
        EventInputParams returnedEventInputParams = null;
        try {
            returnedEventInputParams = defaultPreProcessor.process(eventInputParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(returnedEventInputParams, is(notNullValue()));
        assertThat(eventInputParams, is(returnedEventInputParams));
    }
}