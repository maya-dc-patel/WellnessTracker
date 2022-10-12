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

import edu.ncsu.csc.WellnessTracker.models.Entry;
import edu.ncsu.csc.WellnessTracker.services.EntryService;

@RestController
public class APIEntryController extends APIController {

    /**
     * IngredientService object, to be autowired in by Spring to allow for
     * manipulating the Ingredient model
     */
    @Autowired
    private EntryService service;

    /**
     * REST API method to provide GET access to all recipes in the system
     *
     * @return JSON representation of all recipies
     */
    @CrossOrigin ( origins = "http://localhost:4200" )

    @GetMapping ( BASE_PATH + "/entries" )
    public List<Entry> getAllEntries () {
        return service.findAll();
    }

    /**
     * REST API endpoint to provide GET access to the CoffeeMaker's ingredient.
     *
     *
     * @return response to the request
     */
    @CrossOrigin ( origins = "http://localhost:4200" )

    @GetMapping ( BASE_PATH + "/entries/{name}" )
    public ResponseEntity getEntry ( @PathVariable final String date ) {
        System.out.println( "line 38" + date );
        System.out.println( service.findAll() );
        final Entry entr = service.findByDate( date );
        System.out.println( "line 40" + entr );

        if ( null == entr ) {
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        }

        return new ResponseEntity( entr, HttpStatus.OK );
    }

    /**
     * REST API method to provide POST access to the Ingredient model. This is
     * used to create a new Ingredient by automatically converting the JSON
     * RequestBody provided to a Ingredient object. Invalid JSON will fail.
     *
     * @param entry
     *            The valid Ingredient to be saved.
     * @return ResponseEntity indicating success if the Ingredient could be
     *         saved, or an error if it could not be
     */
    @CrossOrigin ( origins = "http://localhost:4200" )

    @PostMapping ( BASE_PATH + "/entries" )
    public ResponseEntity createEntry ( @RequestBody final Entry entry ) {
        System.out.println( entry );
        final Entry db = service.findByDate( entry.getDate() );
        System.out.println( db );

        if ( null != db ) {
            return new ResponseEntity( HttpStatus.CONFLICT );
        }

        try {
            service.save( entry );
            return new ResponseEntity( HttpStatus.CREATED );
        }
        catch ( final Exception e ) {
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
    @DeleteMapping ( BASE_PATH + "/entries/{name}" )
    public ResponseEntity deleteIngredient ( @PathVariable final String date ) {
        final Entry entry = service.findByDate( date );
        System.out.println( "2 - " + entry.toString() );
        if ( null == entry ) {
            return new ResponseEntity( errorResponse( "No entry found for name " + date ), HttpStatus.NOT_FOUND );
        }
        service.delete( entry );

        return new ResponseEntity( successResponse( date + " was deleted successfully" ), HttpStatus.OK );

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
     * @param newIngredient
     *            amounts to add to inventory
     * @return response to the request
     */
    @PutMapping ( BASE_PATH + "/entries/{name}" )
    public ResponseEntity updateEntry ( @RequestBody final Entry newEntry ) {

        final Entry entry = service.findByDate( newEntry.getDate() );
        entry.setDate( newEntry.getDate() );

        entry.setContent( newEntry.getContent() );
        entry.setDate( newEntry.getDate() );

        service.save( entry );
        return new ResponseEntity( entry, HttpStatus.OK );

    }
}
