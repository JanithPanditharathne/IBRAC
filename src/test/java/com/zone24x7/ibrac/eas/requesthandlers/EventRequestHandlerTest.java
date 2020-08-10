package com.zone24x7.ibrac.eas.requesthandlers;

import com.zone24x7.ibrac.eas.converters.DefaultRequestConverter;
import com.zone24x7.ibrac.eas.converters.RequestConverter;
import com.zone24x7.ibrac.eas.converters.RequestConverterProvider;
import com.zone24x7.ibrac.eas.formaters.DefaultRequestFormatter;
import com.zone24x7.ibrac.eas.formaters.RequestFormatter;
import com.zone24x7.ibrac.eas.formaters.RequestFormatterProvider;
import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.processors.DefaultPreProcessor;
import com.zone24x7.ibrac.eas.processors.PreProcessor;
import com.zone24x7.ibrac.eas.processors.PreProcessorProvider;
import com.zone24x7.ibrac.eas.util.StringConstants;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventRequestHandlerTest {
    private EventRequestHandler eventRequestHandler;
    private RequestConverter requestConverter;
    private RequestConverterProvider requestConverterProvider;
    private EventInputParams eventInputParams;
    private RequestFormatterProvider requestFormatterProvider;
    private RequestFormatter requestFormatter;
    private PreProcessorProvider preProcessorProvider;
    private PreProcessor preprocessor;


    private final String topicName = "rectrack";
    private final String request = "request";
    private final String requestId = "12345";

    /**
     * Method to setup the dependencies for the test class
     */
    @BeforeEach
    void setup() {
        eventRequestHandler = new EventRequestHandler();
        eventInputParams = new EventInputParams(requestId,topicName, request, StringConstants.TEXT_PLAIN);

        requestConverterProvider = mock(RequestConverterProvider.class);
        requestFormatterProvider = mock(RequestFormatterProvider.class);
        preProcessorProvider = mock(PreProcessorProvider.class);

        requestConverter = mock(DefaultRequestConverter.class);
        requestFormatter = mock(DefaultRequestFormatter.class);
        preprocessor = mock(DefaultPreProcessor.class);

        ReflectionTestUtils.setField(eventRequestHandler, "requestConverterProvider", requestConverterProvider);
        ReflectionTestUtils.setField(eventRequestHandler, "requestFormatterProvider", requestFormatterProvider);
        ReflectionTestUtils.setField(eventRequestHandler, "preProcessorProvider", preProcessorProvider);


    }

    /**
     * Test to verify that the topic validator method returns true for an existing topic.
     */
    @Test
    void should_return_the_same_request_string_for_rectrack_topic() throws IOException {

        when(requestConverterProvider.get(topicName)).thenReturn(requestConverter);
        when(requestConverter.convert(eventInputParams)).thenReturn(eventInputParams);
        when(requestFormatterProvider.get(topicName)).thenReturn(requestFormatter);
        when(requestFormatter.format(eventInputParams)).thenReturn(eventInputParams);
        when(preProcessorProvider.get(topicName)).thenReturn(preprocessor);
        when(preprocessor.process(eventInputParams)).thenReturn(eventInputParams);
        String result = eventRequestHandler.handleRequest(eventInputParams);
        assertThat(result, is(eventInputParams.getEventData()));
    }

}