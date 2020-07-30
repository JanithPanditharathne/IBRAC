package com.zone24x7.ibrac.eas;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

class SpringMainConfigTest {

    /**
     * Test to verify that the method returns a valid list for a valid string with topics.
     */
    @Test
    public void should_return_correctlist_when_passing_validtopics() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList("rectrack,topic1,topic2");
        assertThat(listOfWhitelistedTopics, hasItems("rectrack", "topic1", "topic2"));
    }

    /**
     * Test to verify that the method returns a valid list for a valid string with topics that have spaces in the front.
     */
    @Test
    public void should_return_list_of_items_without_spaces_when_passing_topics_with_spaces_in_front() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList(" rectrack, topic1, topic2");
        assertThat(listOfWhitelistedTopics, hasItems("rectrack", "topic1", "topic2"));
    }

    /**
     * Test to verify that the method returns a valid list for a valid string with topics that have spaces in the back.
     */
    @Test
    public void should_return_list_of_items_without_spaces_when_passing_topics_with_spaces_in_back() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList("rectrack ,topic1 ,topic2 ");
        assertThat(listOfWhitelistedTopics, hasItems("rectrack", "topic1", "topic2"));
    }

    /**
     * Test to verify that the method returns a valid list for a valid string with topics that have spaces on both sides.
     */
    @Test
    public void should_return_list_of_items_without_spaces_when_passing_topics_with_spaces_on_both_sides() {
        SpringMainConfig springMainConfig = new SpringMainConfig();
        List<String> listOfWhitelistedTopics = springMainConfig.provideWhitelistedTopicList(" rectrack , topic1 , topic2 ");
        assertThat(listOfWhitelistedTopics, hasItems("rectrack", "topic1", "topic2"));
    }
}