package com.zone24x7.ibrac.eas.formaters;

import com.zone24x7.ibrac.eas.TopicConfig;
import com.zone24x7.ibrac.eas.util.AppConfigStringConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test RequestFormatterProvider.
 */
class RequestFormatterProviderTest {
    private RequestFormatterProvider requestFormatterProvider;
    private RequestFormatter defaultRequestFormatter = mock(RequestFormatter.class);
    private RequestFormatter requestFormatter1 = mock(RequestFormatter.class);

    /**
     * Setup method.
     */
    @BeforeEach
    void setup() {
        Map<String, RequestFormatter> requestFormatterMap = new HashMap<>();
        requestFormatterMap.put(null, defaultRequestFormatter);
        requestFormatterMap.put("default", defaultRequestFormatter);
        requestFormatterMap.put("formatter1", requestFormatter1);

        Map<String, String> topicConfigurationMap = new HashMap<>();
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic1" + AppConfigStringConstants.CONFIG_TOPIC_FORMATTER, "default");
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic2" + AppConfigStringConstants.CONFIG_TOPIC_FORMATTER, "formatter1");
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic3" + AppConfigStringConstants.CONFIG_TOPIC_FORMATTER, "invalid_formatter");

        TopicConfig topicConfig = mock(TopicConfig.class);
        when(topicConfig.getConfigurations()).thenReturn(topicConfigurationMap);

        requestFormatterProvider = new RequestFormatterProvider();
        ReflectionTestUtils.setField(requestFormatterProvider, "requestFormatterMap", requestFormatterMap);
        ReflectionTestUtils.setField(requestFormatterProvider, "topicConfig", topicConfig);
    }

    /**
     * Test to verify that the default formatter is returned when the topic name is not mentioned in the configurations.
     */
    @Test
    void should_return_default_formatter_if_topic_name_is_not_in_configurations() {
        RequestFormatter result = requestFormatterProvider.get("topicNameWithoutConfiguration");
        // AssertThat that the defaultRequestFormatter is returned.
        assertThat(result, is(equalTo(defaultRequestFormatter)));
    }

    /**
     * Test to verify that the default formatter is returned when the topic name has the default formatter mentioned.
     */
    @Test
    void should_return_default_formatter_if_topic_name_has_default_formatter() {
        RequestFormatter result = requestFormatterProvider.get("topic1");
        // AssertThat that the defaultRequestFormatter is returned.
        assertThat(result, is(equalTo(defaultRequestFormatter)));
    }

    /**
     * Test to verify that the correct formatter is returned according to the configuration.
     */
    @Test
    void should_return_correct_formatter_if_according_to_the_topic_configuration() {
        RequestFormatter result = requestFormatterProvider.get("topic2");
        // AssertThat that the defaultRequestFormatter is returned.
        assertThat(result, is(equalTo(requestFormatter1)));
    }

    /**
     * Test to verify that the null is returned when the configuration has an invalid formatter which does not exist.
     */
    @Test
    void should_return_null_if_a_wrong_formatter_is_mentioned_in_configuration_which_does_not_exist() {
        RequestFormatter result = requestFormatterProvider.get("topic3");
        // AssertThat that null is returned when a topic that doesnt exist is passed.
        assertThat(result, is(equalTo(null)));
    }
}