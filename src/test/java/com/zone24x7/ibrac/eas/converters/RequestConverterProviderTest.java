package com.zone24x7.ibrac.eas.converters;

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
 * Test class for RequestConverterProvider.
 */
public class RequestConverterProviderTest {
    private RequestConverterProvider requestConverterProvider;
    private RequestConverter defaultRequestConverter = mock(RequestConverter.class);
    private RequestConverter requestConverter1 = mock(RequestConverter.class);

    /**
     * Setup method.
     */
    @BeforeEach
    public void setup() {
        Map<String, RequestConverter> requestConverterMap = new HashMap<>();
        requestConverterMap.put(null, defaultRequestConverter);
        requestConverterMap.put("default", defaultRequestConverter);
        requestConverterMap.put("converter1", requestConverter1);

        Map<String, String> topicConfigurationMap = new HashMap<>();
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic1" + AppConfigStringConstants.CONFIG_TOPIC_CONVERTER, "default");
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic2" + AppConfigStringConstants.CONFIG_TOPIC_CONVERTER, "converter1");
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic3" + AppConfigStringConstants.CONFIG_TOPIC_CONVERTER, "invalid_converter");

        TopicConfig topicConfig = mock(TopicConfig.class);
        when(topicConfig.getConfigurations()).thenReturn(topicConfigurationMap);

        requestConverterProvider = new RequestConverterProvider();
        ReflectionTestUtils.setField(requestConverterProvider, "requestConverterMap", requestConverterMap);
        ReflectionTestUtils.setField(requestConverterProvider, "topicConfig", topicConfig);
    }

    /**
     * Test to verify that the default converter is returned when the topic name is not mentioned in the configurations.
     */
    @Test
    public void should_return_default_converter_if_topic_name_is_not_in_configurations() {
        RequestConverter result = requestConverterProvider.get("topicNameWithoutConfiguration");
        assertThat(result, is(equalTo(defaultRequestConverter)));
    }

    /**
     * Test to verify that the default converter is returned when the topic name has the default converter mentioned.
     */
    @Test
    public void should_return_default_converter_if_topic_name_has_default_converter() {
        RequestConverter result = requestConverterProvider.get("topic1");
        assertThat(result, is(equalTo(defaultRequestConverter)));
    }

    /**
     * Test to verify that the correct converter is returned according to the configuration.
     */
    @Test
    public void should_return_correct_converter_if_according_to_the_topic_configuration() {
        RequestConverter result = requestConverterProvider.get("topic2");
        assertThat(result, is(equalTo(requestConverter1)));
    }

    /**
     * Test to verify that the null is returned when the configuration has an invalid converter which does not exist.
     */
    @Test
    public void should_return_null_if_a_wrong_converter_is_mentioned_in_configuration_which_does_not_exist() {
        RequestConverter result = requestConverterProvider.get("topic3");
        assertThat(result, is(equalTo(null)));
    }
}