package com.zone24x7.ibrac.eas;

import com.zone24x7.ibrac.eas.util.CorrelationInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.mockito.Mockito.*;

/**
 * Class to test the rec web mvc configurer.
 */
class RecWebMvcConfigurerTest {

    private RecWebMvcConfigurer recWebMvcConfigurer;
    private CorrelationInterceptor correlationInterceptor;
    private InterceptorRegistry registry;
    private CorsRegistry corsRegistry;
    private CorsRegistration corsRegistration;

    private String pathPattern = "/event-accumulator/**";

    /**
     * Method to setup the dependencies for the test class
     */
    @BeforeEach
    void setup(){
        recWebMvcConfigurer = new RecWebMvcConfigurer();
        registry= mock(InterceptorRegistry.class);
        corsRegistry = mock(CorsRegistry.class);
        corsRegistration = mock(CorsRegistration.class);
        correlationInterceptor = mock(CorrelationInterceptor.class);

        ReflectionTestUtils.setField(recWebMvcConfigurer, "correlationInterceptor", correlationInterceptor);
    }

    /**
     * Test to verify that the correlation interceptor added to parameterized registry.
     */
    @Test
    void should_add_correlation_interceptor_to_parameterize_registry() {
        recWebMvcConfigurer.addInterceptors(registry);
        // Verify that the correlation interceptor is added to the parameterized registry
        verify(registry).addInterceptor(correlationInterceptor);
    }

    /**
     * Test to verify that the cores mapping added successfully.
     */
    @Test
    void should_add_cores_mapping_successfully() {
        when(corsRegistry.addMapping(pathPattern)).thenReturn(corsRegistration);
        when(corsRegistration.allowedOrigins("*")).thenReturn(corsRegistration);
        when(corsRegistration.allowedHeaders("Content-Type")).thenReturn(corsRegistration);
        when(corsRegistration.allowedHeaders("GET")).thenReturn(corsRegistration);
        recWebMvcConfigurer.addCorsMappings(corsRegistry);
        // Verify that the cors mapping added successfully.
        verify(corsRegistry).addMapping("/event-accumulator/**");
    }
}
