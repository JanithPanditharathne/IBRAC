package com.zone24x7.ibrac.eas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class TopicConfigTest {
    private TopicConfig topicConfig = null;

    @BeforeEach
    public void setup() {
        topicConfig = new TopicConfig();
    }

    /**
     * Test to verify that the getConfigurations method returns an empty Map when called without populate() method.
     */
    @Test
    public void should_return_empty_recon_lib_map_when_called_without_initialization() {
        Map<String, String> returnedHashmap = topicConfig.getConfigurations();
        assertThat(returnedHashmap, is(notNullValue()));
    }

    /**
     * Test to verify that true is returned when the same object is compared.
     */
    @Test
    public void should_return_true_if_objects_are_equal() {
        boolean returnedResult = topicConfig.equals(topicConfig);
        assertThat(returnedResult, is(true));
    }

    /**
     * Test to verify that false is returned when different Objects are compared.
     */
    @Test
    public void should_return_false_if_objects_are_not_equal() {
        Random differentTopicConfigObject = new Random();
        boolean returnedResult = topicConfig.equals(differentTopicConfigObject);
        assertThat(returnedResult, is(false));
    }

    /**
     * Test to verify that false is returned when null is passed as the parameter.
     */
    @Test
    public void should_return_false_if_null_is_passed() {
        boolean returnedResult = topicConfig.equals(null);
        assertThat(returnedResult, is(false));
    }
}