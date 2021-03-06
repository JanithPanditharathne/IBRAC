package com.zone24x7.ibrac.eas.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.slf4j.spi.MDCAdapter;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

/**
 * Class to test the topic config class.
 */
class CorrelationInterceptorTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;
    private CorrelationInterceptor correlationInterceptor;
    private Logger logger;

    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    private static final String CORRELATION_ID_MDC_ATTRIBUTE_NAME = "correlationId";
    private static final String START_DATE_TIME_MDC_ATTRIBUTE_NAME = "startTime";

    private String correlationId = "1234";
    private long startTime = 123;
    private String emptyCorrelationId = "";
    private MDCAdapter mdcAdapter;

    @BeforeEach
    public void setup() {
        correlationInterceptor = new CorrelationInterceptor();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        logger = mock(Logger.class);

        mdcAdapter = mock(MDCAdapter.class);
        ReflectionTestUtils.setField(MDC.class, "mdcAdapter", mdcAdapter);
    }

    /**
     * Test to verify that the correlation id is getting from the header correctly.
     */
    @Test
    void should_get_the_correlation_id_from_header_correctly() {
        when(request.getHeader(CORRELATION_ID_HEADER_NAME)).thenReturn(correlationId);
        boolean returnValue = correlationInterceptor.preHandle(request, response, handler);
        // AssertThat the correlationInterceptor returns true.
        assertThat(returnValue, is(true));
    }

    /**
     * Test to verify that the correlation id is getting from mdc adapter when x-correlation id is empty in header.
     */
    @Test
    void should_get_the_correlation_id_from_mdcAdapter_when_header_x_correlation_id_is_empty() {
        when(request.getHeader(CORRELATION_ID_HEADER_NAME)).thenReturn(emptyCorrelationId);
        boolean returnValue = correlationInterceptor.preHandle(request, response, handler);
        // AssertThat the correlationInterceptor returns true when the correlation id is empty
        assertThat(returnValue, is(true));
    }

    /**
     * Test to verify that the after completion method working correctly.
     */
    @Test
    void should_execute_after_completion_successfully() {
        when(MDC.get(CORRELATION_ID_MDC_ATTRIBUTE_NAME)).thenReturn(correlationId);
        when(MDC.get(START_DATE_TIME_MDC_ATTRIBUTE_NAME)).thenReturn(correlationId);
        correlationInterceptor.afterCompletion(request,response,handler,new Exception());
        // Test to Verify that the remove method works properly.
        verify(mdcAdapter).remove(START_DATE_TIME_MDC_ATTRIBUTE_NAME);
    }
}
