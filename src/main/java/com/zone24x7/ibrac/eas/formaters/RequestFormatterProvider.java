package com.zone24x7.ibrac.eas.formaters;

import com.zone24x7.ibrac.eas.TopicConfig;
import com.zone24x7.ibrac.eas.util.AppConfigStringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class to represent the request formatter provider.
 */
@Component
public class RequestFormatterProvider {
    @Autowired
    @Qualifier("requestFormatterMap")
    private Map<String, RequestFormatter> requestFormatterMap;

    @Autowired
    private TopicConfig topicConfig;

    /**
     * Method to get the request formatter.
     *
     * @param topicName the topic name
     * @return the request formatter
     */
    public RequestFormatter get(String topicName) {
        // Concatenate the prefix+"."+formatter of the formatter and store it in formatterName
        String formatterName = topicConfig.getConfigurations().get(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + topicName + AppConfigStringConstants.CONFIG_TOPIC_FORMATTER);
        // Return the relevant formatterName stored in the requestFormatterMap.
        return requestFormatterMap.get(formatterName);
    }
}
