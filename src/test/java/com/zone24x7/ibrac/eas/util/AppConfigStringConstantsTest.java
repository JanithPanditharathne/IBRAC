package com.zone24x7.ibrac.eas.util;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class to test the App config string constant class.
 */
class AppConfigStringConstantsTest {

    public static final String CONFIG_TOPIC_PREFIX = "eas.topics";
    public static final String CONFIG_TOPIC_CONVERTER = ".converter";
    public static final String CONFIG_TOPIC_PREPROCESSOR = ".preprocessor";
    public static final String CONFIG_TOPIC_FORMATTER = ".formatter";

    /**
     * Test to verify that the correct config text returns from the AppConfigStringConstants class
     */
    @Test
    void should_return_the_strings_by_the_app_config_string_constants() {
        assertThat(AppConfigStringConstants.CONFIG_TOPIC_PREFIX, is(AppConfigStringConstantsTest.CONFIG_TOPIC_PREFIX));
        assertThat(AppConfigStringConstants.CONFIG_TOPIC_CONVERTER, is(AppConfigStringConstantsTest.CONFIG_TOPIC_CONVERTER));
        assertThat(AppConfigStringConstants.CONFIG_TOPIC_PREPROCESSOR, is(AppConfigStringConstantsTest.CONFIG_TOPIC_PREPROCESSOR));
        assertThat(AppConfigStringConstants.CONFIG_TOPIC_FORMATTER, is(AppConfigStringConstantsTest.CONFIG_TOPIC_FORMATTER));
    }
}
