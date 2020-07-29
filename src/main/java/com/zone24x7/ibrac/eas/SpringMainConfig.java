package com.zone24x7.ibrac.eas;

import com.zone24x7.ibrac.eas.converters.DefaultRequestConverter;
import com.zone24x7.ibrac.eas.converters.RequestConverter;
import com.zone24x7.ibrac.eas.formaters.DefaultRequestFormatter;
import com.zone24x7.ibrac.eas.formaters.RequestFormatter;
import com.zone24x7.ibrac.eas.processors.DefaultPreProcessor;
import com.zone24x7.ibrac.eas.processors.PreProcessor;
import com.zone24x7.ibrac.eas.util.StringConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, RequestConverter> requestConverterMap = new HashMap<>();
        requestConverterMap.put(null, defaultRequestConverter);
        requestConverterMap.put(StringConstants.DEFAULT_KEY, defaultRequestConverter);
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
        Map<String, RequestFormatter> requestFormatterMap = new HashMap<>();
        requestFormatterMap.put(null, defaultRequestFormatter);
        requestFormatterMap.put(StringConstants.DEFAULT_KEY, defaultRequestFormatter);
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
        Map<String, PreProcessor> preProcessorMap = new HashMap<>();
        preProcessorMap.put(null, defaultPreProcessor);
        preProcessorMap.put(StringConstants.DEFAULT_KEY, defaultPreProcessor);
        return preProcessorMap;
    }
}