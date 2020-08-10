package com.zone24x7.ibrac.eas;

import com.zone24x7.ibrac.eas.converters.DefaultRequestConverter;
import com.zone24x7.ibrac.eas.converters.RequestConverter;
import com.zone24x7.ibrac.eas.formaters.DefaultRequestFormatter;
import com.zone24x7.ibrac.eas.formaters.RequestFormatter;
import com.zone24x7.ibrac.eas.processors.DefaultPreProcessor;
import com.zone24x7.ibrac.eas.processors.PreProcessor;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SpringMainConfigTest {
    private final List<String> whiteListedTopicsList = new ArrayList<>(Arrays.asList("rectrack", "topic1", "topic2"));

    /**
     * Test to verify that the method returns a valid list for a valid string with topics.
     */
    @Test
    void should_return_correctlist_when_passing_validtopics() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList("rectrack,topic1,topic2");
        assertThat(listOfWhitelistedTopics, equalTo(whiteListedTopicsList));
    }

    /**
     * Test to verify that the method returns a valid list for a valid string with topics that have spaces in the front.
     */
    @Test
    void should_return_list_of_items_without_spaces_when_passing_topics_with_spaces_in_front() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList(" rectrack, topic1, topic2");
        assertThat(listOfWhitelistedTopics, equalTo(whiteListedTopicsList));
    }

    /**
     * Test to verify that the method returns a valid list for a valid string with topics that have spaces in the back.
     */
    @Test
    void should_return_list_of_items_without_spaces_when_passing_topics_with_spaces_in_back() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList("rectrack ,topic1 ,topic2 ");
        assertThat(listOfWhitelistedTopics, equalTo(whiteListedTopicsList));
    }

    /**
     * Test to verify that the method returns a valid list for a valid string with topics that have spaces on both sides.
     */
    @Test
    void should_return_list_of_items_without_spaces_when_passing_topics_with_spaces_on_both_sides() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList(" rectrack , topic1 , topic2 ");
        assertThat(listOfWhitelistedTopics, equalTo(whiteListedTopicsList));
    }

    /**
     * Test to verify that the method returns the valid hashmap of request converters.
     */
    @Test
    void should_return_default_request_converter_map_when_called() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        DefaultRequestConverter defaultRequestConverter = new DefaultRequestConverter();
        Map<String, RequestConverter> returnedRequestConverterMap = springMainConfig.provideRequestConverterMap(defaultRequestConverter);
        Map<String, RequestConverter> requestConverterMap = new HashMap<>();
        requestConverterMap.put(null, defaultRequestConverter);
        requestConverterMap.put(StringConstants.DEFAULT_KEY, defaultRequestConverter);
        assertThat(returnedRequestConverterMap, equalTo(requestConverterMap));
    }

    /**
     * Test to verify that the method returns the valid hashmap of request formatters.
     */
    @Test
    void should_return_default_request_formatter_map_when_called() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        DefaultRequestFormatter defaultRequestFormatter = new DefaultRequestFormatter();
        Map<String, RequestFormatter> returnedRequestConverterMap = springMainConfig.provideRequestFormatterMap(defaultRequestFormatter);
        Map<String, RequestFormatter> requestFormatterMap = new HashMap<>();
        requestFormatterMap.put(null, defaultRequestFormatter);
        requestFormatterMap.put(StringConstants.DEFAULT_KEY, defaultRequestFormatter);
        assertThat(returnedRequestConverterMap, equalTo(requestFormatterMap));
    }

    /**
     * Test to verify that the method returns the valid hashmap of pre processors.
     */
    @Test
    void should_return_default_preprocessor_map_when_called() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        DefaultPreProcessor defaultPreProcessor = new DefaultPreProcessor();
        Map<String, PreProcessor> returnedPreProcessorMap = springMainConfig.providePreProcessorMap(defaultPreProcessor);
        Map<String, PreProcessor> preProcessorMap = new HashMap<>();
        preProcessorMap.put(null, defaultPreProcessor);
        preProcessorMap.put(StringConstants.DEFAULT_KEY, defaultPreProcessor);
        assertThat(returnedPreProcessorMap, equalTo(preProcessorMap));
    }
}