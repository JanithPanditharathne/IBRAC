package com.zone24x7.ibrac.eas.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class to validate the topic.
 */
@Component
public class TopicValidator {
    @Autowired
    @Qualifier("whitelistedTopicList")
    private List<String> whiteListedTopicList;

    /**
     * Method to validate if the topic is present in the properties file.
     *
     * @param topic topic sent in the post request.
     * @return true if the topic is present, else false.
     */
    public boolean validate(String topic) {
        // Return whether the topic can be found in the whiteListedTopicsList
        return whiteListedTopicList.contains(topic);
    }
}
