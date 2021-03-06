package com.zone24x7.ibrac.eas;

import com.zone24x7.ibrac.eas.util.AppConfigStringConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for injecting topic related configs.
 */
@Configuration
@ConfigurationProperties(prefix = AppConfigStringConstants.CONFIG_TOPIC_PREFIX)
public class TopicConfig extends HashMap<String, String> {
    private static final long serialVersionUID = 862498820763181979L;
    private Map<String, String> configMap = new HashMap<>();

    /**
     * Method to get the topic configurations map.
     *
     * @return the topic configurations map
     */
    public Map<String, String> getConfigurations() {
        return this.configMap;
    }

    /**
     * Method to populate the configuration map for topics.
     */
    @PostConstruct
    private void populateMap() {
        // If the configMap is empty it will populate the config map from the properties file.
        if (configMap.isEmpty()) {
            this.forEach((key, value) -> configMap.put(AppConfigStringConstants.CONFIG_TOPIC_PREFIX + "." + key, value));
        }
    }

    /**
     * Overridden equals method.
     *
     * @param o object to compare
     * @return true if equals and false if not
     */
    @Override
    public boolean equals(Object o) {
        // If it is the same object being compared, return true
        if (this == o) {
            return true;
        }

        // if the object is null or if it is of different types, return null.
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // If the super classes are not equal return false.
        if (!super.equals(o)) {
            return false;
        }

        TopicConfig that = (TopicConfig) o;

        return configMap != null ? configMap.equals(that.configMap) : that.configMap == null;
    }

    /**
     * Overridden hashcode method.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (configMap != null ? configMap.hashCode() : 0);
        return result;
    }
}
