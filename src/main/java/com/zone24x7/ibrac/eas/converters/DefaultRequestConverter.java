package com.zone24x7.ibrac.eas.converters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Implementation class to represent the default RequestConverter
 */
@Component
@Qualifier("defaultRequestConverter")
public class DefaultRequestConverter implements RequestConverter {

}
