package com.zone24x7.ibrac.eas.processors;

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
 * Class to test PreProcessorProvider.
 */
class PreProcessorProviderTest {
    private PreProcessorProvider preProcessorProvider;
    private PreProcessor defaultPreProcessor = mock(PreProcessor.class);
    private PreProcessor preProcessor1 = mock(PreProcessor.class);

    /**
     * Setup method.
     */
    @BeforeEach
    public void setup() {
        Map<String, PreProcessor> preProcessorMap = new HashMap<>();
        preProcessorMap.put(null, defaultPreProcessor);
        preProcessorMap.put("default", defaultPreProcessor);
        preProcessorMap.put("preProcessor1", preProcessor1);

        Map<String, String> topicConfigurationMap = new HashMap<>();
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic1" + AppConfigStringConstants.CONFIG_TOPIC_PREPROCESSOR, "default");
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic2" + AppConfigStringConstants.CONFIG_TOPIC_PREPROCESSOR, "preProcessor1");
        topicConfigurationMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + "topic3" + AppConfigStringConstants.CONFIG_TOPIC_PREPROCESSOR, "invalid_preprocessor");

        TopicConfig topicConfig = mock(TopicConfig.class);
        when(topicConfig.getConfigurations()).thenReturn(topicConfigurationMap);

        preProcessorProvider = new PreProcessorProvider();
        ReflectionTestUtils.setField(preProcessorProvider, "preProcessorMap", preProcessorMap);
        ReflectionTestUtils.setField(preProcessorProvider, "topicConfig", topicConfig);
    }

    /**
     * Test to verify that the default preprocessor is returned when the topic name is not mentioned in the configurations.
     */
    @Test
    void should_return_default_preprocessor_if_topic_name_is_not_in_configurations() {
        PreProcessor result = preProcessorProvider.get("topicNameWithoutConfiguration");
        assertThat(result, is(equalTo(defaultPreProcessor)));
    }

    /**
     * Test to verify that the default preprocessor is returned when the topic name has the default preprocessor mentioned.
     */
    @Test
    void should_return_default_preprocessor_if_topic_name_has_default_preprocessor() {
        PreProcessor result = preProcessorProvider.get("topic1");
        assertThat(result, is(equalTo(defaultPreProcessor)));
    }

    /**
     * Test to verify that the correct preprocessor is returned according to the configuration.
     */
    @Test
    void should_return_correct_preprocessor_if_according_to_the_topic_configuration() {
        PreProcessor result = preProcessorProvider.get("topic2");
        assertThat(result, is(equalTo(preProcessor1)));
    }

    /**
     * Test to verify that the null is returned when the configuration has an invalid preprocessor which does not exist.
     */
    @Test
    void should_return_null_if_a_wrong_preprocessor_is_mentioned_in_configuration_which_does_not_exist() {
        PreProcessor result = preProcessorProvider.get("topic3");
        assertThat(result, is(equalTo(null)));
    }
}