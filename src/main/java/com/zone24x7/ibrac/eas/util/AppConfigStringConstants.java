package com.zone24x7.ibrac.eas.util;

/**
 * Publicly used String constants values are stored here.
 */
public final class AppConfigStringConstants {

    public static final String CONFIG_TOPIC_PREFIX = "eas.topics";
    public static final String CONFIG_TOPIC_CONVERTER = ".converter";
    public static final String CONFIG_TOPIC_PREPROCESSOR = ".preprocessor";
    public static final String CONFIG_TOPIC_FORMATTER = ".formatter";


    /**
     * Private constructor to stop initiation
     */
    private AppConfigStringConstants() {
        throw new IllegalStateException("AppConfigStringConstants is a utility class");
    }
}
