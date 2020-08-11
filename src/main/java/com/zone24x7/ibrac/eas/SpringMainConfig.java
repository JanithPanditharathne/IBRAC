package com.zone24x7.ibrac.eas;

import com.zone24x7.ibrac.eas.converters.DefaultRequestConverter;
import com.zone24x7.ibrac.eas.converters.RequestConverter;
import com.zone24x7.ibrac.eas.formaters.DefaultRequestFormatter;
import com.zone24x7.ibrac.eas.formaters.RequestFormatter;
import com.zone24x7.ibrac.eas.processors.DefaultPreProcessor;
import com.zone24x7.ibrac.eas.processors.PreProcessor;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Class to represent the spring main configurations and bindings.
 */
@Configuration
public class SpringMainConfig {
    /**
     * Method to provide the request converter map.
     *
     * @param defaultRequestConverter the default request converter
     * @return the request converter
     */
    @Bean
    @Qualifier("requestConverterMap")
    public Map<String, RequestConverter> provideRequestConverterMap(DefaultRequestConverter defaultRequestConverter) {
        // Create a new HashMap
        Map<String, RequestConverter> requestConverterMap = new HashMap<>();
        // Put the defaultRequestConverter with null as the key
        requestConverterMap.put(null, defaultRequestConverter);
        // Put the defaultRequestConverter with the DEFAULT_KEY
        requestConverterMap.put(StringConstants.DEFAULT_KEY, defaultRequestConverter);
        // Return the populated Map of converters
        return requestConverterMap;
    }

    /**
     * Method to provide the request formatter map.
     *
     * @param defaultRequestFormatter the default request formatter
     * @return the request formatter map
     */
    @Bean
    @Qualifier("requestFormatterMap")
    public Map<String, RequestFormatter> provideRequestFormatterMap(DefaultRequestFormatter defaultRequestFormatter) {
        // Create a new HashMap
        Map<String, RequestFormatter> requestFormatterMap = new HashMap<>();
        // Put the defaultRequestFormatter with null as the key
        requestFormatterMap.put(null, defaultRequestFormatter);
        // Put the defaultRequestFormatter with the DEFAULT_KEY
        requestFormatterMap.put(StringConstants.DEFAULT_KEY, defaultRequestFormatter);
        // Return the populated Map of formatters
        return requestFormatterMap;
    }

    /**
     * Method to provide the pre processor map.
     *
     * @param defaultPreProcessor the default pre processor.
     * @return the pre processor map
     */
    @Bean
    @Qualifier("preProcessorMap")
    public Map<String, PreProcessor> providePreProcessorMap(DefaultPreProcessor defaultPreProcessor) {
        // Create a new HashMap
        Map<String, PreProcessor> preProcessorMap = new HashMap<>();
        // Put the defaultPreProcessor with null as the key
        preProcessorMap.put(null, defaultPreProcessor);
        // Put the defaultPreProcessor with the DEFAULT_KEY
        preProcessorMap.put(StringConstants.DEFAULT_KEY, defaultPreProcessor);
        // Return the populated Map of preProcessors
        return preProcessorMap;
    }

    /**
     * Method to provide the whitelisted topic list
     *
     * @param whitelistedTopicsConfiguration Configuration string for whitelisted topic list
     * @return whitelistedTopicsList
     */
    @Bean
    @Qualifier("whitelistedTopicList")
    public List<String> provideWhitelistedTopicList(@Value("${eas.whitelisted.topics}") String whitelistedTopicsConfiguration) {
        // Create a new LinkedList
        List<String> whitelistedTopicList = new LinkedList<>();

        // If the whiteListedTopics found in the properties file is not empty
        if (StringUtils.isNotEmpty(whitelistedTopicsConfiguration)) {
            // Split the string using commas into individual strings of topics and add it to the linked list.
            whitelistedTopicList.addAll(Arrays.asList(whitelistedTopicsConfiguration.trim().split("\\s*,\\s*")));
        }

        return whitelistedTopicList;
    }
}