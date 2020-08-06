package com.zone24x7.ibrac.eas.requesthandlers;

import com.zone24x7.ibrac.eas.converters.RequestConverterProvider;
import com.zone24x7.ibrac.eas.formaters.RequestFormatterProvider;
import com.zone24x7.ibrac.eas.pojo.EventInputParams;
import com.zone24x7.ibrac.eas.processors.PreProcessorProvider;
import com.zone24x7.ibrac.eas.util.StringConstants;
import com.zone24x7.ibrac.eas.util.TopicValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class EventRequestHandlerTest {
    private EventRequestHandler eventRequestHandler;
    private EventInputParams eventInputParams;
    private TopicValidator topicValidator;
    private RequestConverterProvider requestConverterProvider;
    private PreProcessorProvider preProcessorProvider;
    private RequestFormatterProvider requestFormatterProvider;


    private final String topicName = "rectrack";
    private final String request = "request";
    private final String requestId = "12345";

    /**
     * Method to setup the dependencies for the test class
     */
    @BeforeEach
    void setup() {
        eventRequestHandler = mock(EventRequestHandler.class);
        eventInputParams = new EventInputParams(requestId, topicName, request, StringConstants.TEXT_PLAIN);
        topicValidator = new TopicValidator();
        requestConverterProvider = mock(RequestConverterProvider.class);
        requestFormatterProvider = mock(RequestFormatterProvider.class);
        preProcessorProvider = mock(PreProcessorProvider.class);

        List<String> whitelistedTopicList = new LinkedList<>();
        whitelistedTopicList.add("rectrack");
        ReflectionTestUtils.setField(topicValidator, "whiteListedTopicList", whitelistedTopicList);
    }

    /**
     * Test to verify that the topic validator method returns true for an existing topic.
     */
    @Test
    public void should_return_the_same_string_for_rectrack_topic() {
        //String output = null;
        //try {
        //output = eventRequestHandler.handleRequest(eventInputParams);
        //System.out.println(output);
        //} catch (IOException e) {
        //e.printStackTrace();
        //}
        //assertThat(output, is(request));
    }

}