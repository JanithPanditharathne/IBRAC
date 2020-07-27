package com.zone24x7.ibrac.eas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the application where the application will bootstrap.
 */
@SpringBootApplication
public class EventAccumulatorApplication {

    /**
     * Main method of tha application to for bootstrapping application.
     *
     * @param args input arguments.
     */
    public static void main(String[] args) { // NOSONAR
        SpringApplication.run(EventAccumulatorApplication.class, args); // NOSONAR
    }
}
