package edu.ncsu.csc.CoffeeMaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entrypoint to the CoffeeMaker Application. Allows running as Java
 * application.
 *
 * @author Kai Presler-Marshall (kpresle@ncsu.edu0
 *
 */
@SpringBootApplication ( scanBasePackages = { "edu.ncsu.csc.CoffeeMaker" } )
public class CoffeeMakerApplication {

    /**
     * Main method
     *
     * @param args
     *            Command-line args
     */
    public static void main ( final String[] args ) {
        // CoffeeMakerApplication.createSomeIngredients();
        System.out.println("This is my application.");
        SpringApplication.run( CoffeeMakerApplication.class, args );

    }



    // public static void createSomeIngredients() {
    // IngredientService ingredientService = new Ingredient
    // final Ingredient i1 = new Ingredient( "COFFEE", 5 );
    //
    // ingredientService.save( i1 );
    //
    // final Ingredient i2 = new Ingredient( "MILK", 3 );
    //
    // ingredientService.save( i2 );
    //
    // }

}
