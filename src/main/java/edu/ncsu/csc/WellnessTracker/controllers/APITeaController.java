package edu.ncsu.csc.WellnessTracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.WellnessTracker.models.Journal;
import edu.ncsu.csc.WellnessTracker.models.Tea;
import edu.ncsu.csc.WellnessTracker.services.JournalService;
import edu.ncsu.csc.WellnessTracker.services.TeaService;

@RestController
public class APITeaController extends APIController {

    /**
     * IngredientService object, to be autowired in by Spring to allow for
     * manipulating the Ingredient model
     */
    @Autowired
    private TeaService service;

    /**
     * REST API method to provide GET access to all journals in the system
     *
     * @return JSON representation of all journals
     */
    @CrossOrigin ( origins = "http://localhost:4200" )

    @GetMapping ( BASE_PATH + "/teas" )
    public List<Tea> getAllTeas () {
        return service.findAll();
    }

    /**
     * REST API endpoint to provide GET access to the CoffeeMaker's ingredient.
     *
     *
     * @return response to the request
     */
    @CrossOrigin ( origins = "http://localhost:4200" )

    @GetMapping ( BASE_PATH + "/tea/{name}" )
    public ResponseEntity getTea ( @PathVariable final String name ) {

        final Tea tea = service.findByName( name );

        if ( null == tea ) {
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        }

        return new ResponseEntity( tea, HttpStatus.OK );
    }

    /**
     * REST API method to provide POST access to the Ingredient model. This is
     * used to create a new Ingredient by automatically converting the JSON
     * RequestBody provided to a Ingredient object. Invalid JSON will fail.
     *
     * @param ingredient
     *            The valid Ingredient to be saved.
     * @return ResponseEntity indicating success if the Ingredient could be
     *         saved, or an error if it could not be
     */
    @CrossOrigin ( origins = "http://localhost:4200" )

    @PostMapping ( BASE_PATH + "/teas" )
    public ResponseEntity saveTea ( @RequestBody final Tea tea ) {
        System.out.println("IN SAVE TEA METHOD");
     

        final Tea db = service.findByName( tea.getName() );
        System.out.println("FIND FROM DB: " + db);

        if ( db != null ) {
            return new ResponseEntity( HttpStatus.CONFLICT );
        }

        try {
            System.out.println("line 89");
            service.save( tea );
            System.out.println("REACHED");
            return new ResponseEntity( HttpStatus.CREATED );
        }
        catch ( final Exception e ) {
            System.out.println(e.getMessage() + " " + e.getCause());
            return new ResponseEntity( HttpStatus.BAD_REQUEST ); // HttpStatus.FORBIDDEN
                                                                 // would be OK
                                                                 // too.
        }

    }

    /**
     * REST API method to allow deleting a Ingredient from the CoffeeMaker, by
     * making a DELETE request to the API endpoint and indicating the ingredient
     * to delete (as a path variable)
     *
     * @param name
     *            The name of the Ingredient to delete
     * @return Success if the ingredient could be deleted; an error if the
     *         ingredient does not exist
     */
    @DeleteMapping ( BASE_PATH + "/teas/{name}" )
    public ResponseEntity deleteIngredient ( @PathVariable final String name ) {
        // System.out.println( "1 - " + ingredient.toString() );
        final Tea journal = service.findByName( name );
        System.out.println( "2 - " + journal.toString() );
        if ( null == journal ) {
            return new ResponseEntity( errorResponse( "No journal found for name " + name ), HttpStatus.NOT_FOUND );
        }
        service.delete( journal );

        return new ResponseEntity( successResponse( name + " was deleted successfully" ), HttpStatus.OK );

    }

    // @DeleteMapping ( BASE_PATH + "/ingredients/{ing}" )
    // public ResponseEntity deleteIngredient ( @PathVariable final Ingredient
    // ing ) {
    //
    // final Ingredient ingredient = service.findByName( ing.getIngredient() );
    // if ( null == ingredient ) {
    // return new ResponseEntity( errorResponse( "No ingredient found for name "
    // + name ), HttpStatus.NOT_FOUND );
    // }
    // service.delete( ingredient );
    //
    // return new ResponseEntity( successResponse( name + " was deleted
    // successfully" ), HttpStatus.OK );
    //
    // }

    /**
     * REST API endpoint to provide update access to CoffeeMaker's singleton
     * ingredients. This will update the Ingredient of the CoffeeMaker.
     *
     * @param newJournal
     *            amounts to add to inventory
     * @return response to the request
     */
    @PutMapping ( BASE_PATH + "/tea/{name}" )
    public ResponseEntity updateIngredient ( @RequestBody final Tea newJournal ) {
        final Tea journal = service.findByName( newJournal.getName() );

        journal.setName( newJournal.getName() );
        // journal.setOwner( newJournal.getOwner() );

        service.save( journal );
        return new ResponseEntity( journal, HttpStatus.OK );

    }
}
