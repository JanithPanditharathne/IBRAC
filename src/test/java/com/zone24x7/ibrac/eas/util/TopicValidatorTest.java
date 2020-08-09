package com.zone24x7.ibrac.eas.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class to test the Topic validator class.
 */
public class TopicValidatorTest {

    private TopicValidator topicValidator;


    /**
     * Method to setup the dependencies for the test class
     */
    @BeforeEach
    void setup() {
        topicValidator = new TopicValidator();
        List<String> whitelistedTopicList = new LinkedList<>();
        whitelistedTopicList.add("rectrack");
        ReflectionTestUtils.setField(topicValidator, "whiteListedTopicList", whitelistedTopicList);
    }

    /**
     * Test to verify that the topic validator method returns true for an existing topic.
     */
    @Test
    public void should_returnTrue_for_methodCallWithExistingTopic() {
        assertThat(topicValidator.validate("rectrack"), is(true));
    }

    /**
     * Test to verify that the topic validator method returns false for a topic that doesn't exist.
     */
    @Test
    public void should_returnFalse_for_methodCallWithoutAnExistingTopic() {
        assertThat(topicValidator.validate("abc"), is(false));
    }


}