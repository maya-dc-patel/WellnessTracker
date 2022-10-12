package edu.ncsu.csc.WellnessTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entrypoint to the WellnessTracker Application. Allows running as Java
 * application.
 *
 * @author Kai Presler-Marshall (kpresle@ncsu.edu0
 *
 */
@SpringBootApplication ( scanBasePackages = { "edu.ncsu.csc.WellnessTracker" } )
public class WellnessTrackerApplication {

    /**
     * Main method
     *
     * @param args
     *            Command-line args
     */
    public static void main ( final String[] args ) {
        System.out.println("This is my application.");
        SpringApplication.run( WellnessTrackerApplication.class, args );

    }
    
}
