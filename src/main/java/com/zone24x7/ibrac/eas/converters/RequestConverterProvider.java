package com.zone24x7.ibrac.eas.converters;

import com.zone24x7.ibrac.eas.TopicConfig;
import com.zone24x7.ibrac.eas.util.AppConfigStringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class to represent the request converter provider.
 */
@Component
public class RequestConverterProvider {
    @Autowired
    @Qualifier("requestConverterMap")
    private Map<String, RequestConverter> requestConverterMap;

    @Autowired
    private TopicConfig topicConfig;

    /**
     * Method to get the request converter for the given topic name.
     *
     * @param topicName the topic name
     * @return the request converter
     */
    public RequestConverter get(String topicName) {
        String converterName = topicConfig.getConfigurations().get(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + topicName + AppConfigStringConstants.CONFIG_TOPIC_CONVERTER);
        return requestConverterMap.get(converterName);
    }
}
