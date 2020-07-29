package com.zone24x7.ibrac.eas.processors;

import com.zone24x7.ibrac.eas.TopicConfig;
import com.zone24x7.ibrac.eas.util.AppConfigStringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

/**
 * Class to represent the pre processor provider.
 */
public class PreProcessorProvider {
    @Autowired
    @Qualifier("preProcessorMap")
    private Map<String, PreProcessor> preProcessorMap;

    @Autowired
    private TopicConfig topicConfig;

    /**
     * Method to get the pre processor.
     *
     * @param topicName the topic name
     * @return the pre processor
     */
    public PreProcessor get(String topicName) {
        String preprocessorName = topicConfig.getConfigurations().get(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + topicName + AppConfigStringConstants.CONFIG_TOPIC_PREPROCESSOR);
        return preProcessorMap.get(preprocessorName);
    }
}
