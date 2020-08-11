package com.zone24x7.ibrac.eas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

/**
 * Class to test the topic config class.
 */
class TopicConfigTest {
    private TopicConfig topicConfig = null;
    private TopicConfig topicConfig1 = null;
    private Map<String, String> configMap = new HashMap<>();
    private Map<String, String> hashMap;


    /**
     * Method to setup the dependencies for the test class
     */
    @BeforeEach
    void setup() {
        topicConfig = new TopicConfig();
        configMap.put("eas.topics.rectrack.converter" , "default");
        configMap.put("eas.topics.rectrack.formatter" , "default");
        configMap.put("eas.topics.rectrack.preprocessor" , "default");

        hashMap = mock(Map.class);

        ReflectionTestUtils.setField(topicConfig, "configMap", hashMap);
    }

    /**
     * Test to verify that the getConfigurations method returns an empty Map when called without populate() method.
     */
    @Test
    void should_return_empty_map_when_called_without_initialization() {
        Map<String, String> returnedHashMap = topicConfig.getConfigurations();
        assertThat(returnedHashMap, is(notNullValue()));
    }

    /**
     * Test to verify that true is returned when the same object is compared.
     */
    @Test
    void should_return_true_if_objects_are_equal() {
        boolean returnedResult = topicConfig.equals(topicConfig);
        assertThat(returnedResult, is(true));
    }

    /**
     * Test to verify that false is returned when different Objects are compared.
     */
    @Test
    void should_return_false_if_objects_are_not_equal() {
        Random differentTopicConfigObject = new Random();
        boolean returnedResult = topicConfig.equals(differentTopicConfigObject);
        assertThat(returnedResult, is(false));
    }

    /**
     * Test to verify that false is returned when null is passed as the parameter.
     */
    @Test
    void should_return_false_if_null_is_passed() {
        boolean returnedResult = topicConfig.equals(null);
        assertThat(returnedResult, is(false));
    }

    /**
     * Test to verify that the correct hashcode is returned
     */
    @Test
    void test_to_verify_that_the_correct_hashcode_is_returned() {
        // AssertThat the correct hashcode is returned.
        assertThat(topicConfig.hashCode(),is(hashMap.hashCode()));
    }
}