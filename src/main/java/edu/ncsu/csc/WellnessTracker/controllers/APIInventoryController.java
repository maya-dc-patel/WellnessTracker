package edu.ncsu.csc.WellnessTracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.WellnessTracker.models.Ingredient;
import edu.ncsu.csc.WellnessTracker.models.Inventory;
import edu.ncsu.csc.WellnessTracker.services.InventoryService;

/**
 * This is the controller that holds the REST endpoints that handle add and
 * update operations for the Inventory.
 *
 * Spring will automatically convert all of the ResponseEntity and List results
 * to JSON
 *
 * @author Kai Presler-Marshall
 * @author Michelle Lemons
 *
 */
@SuppressWarnings ( { "unchecked", "rawtypes" } )
@RestController
public class APIInventoryController extends APIController {

    /**
     * InventoryService object, to be autowired in by Spring to allow for
     * manipulating the Inventory model
     */
    @Autowired
    private InventoryService service;

    /**
     * REST API endpoint to provide GET access to the CoffeeMaker's singleton
     * Inventory. This will convert the Inventory to JSON.
     *
     * @return response to the request
     */
    @CrossOrigin ( origins = "http://localhost:4200" )
    @GetMapping ( BASE_PATH + "/inventory" )
    public ResponseEntity getInventory () {
        final Inventory inventory = service.getInventory();
        return new ResponseEntity( inventory, HttpStatus.OK );
    }

    /**
     * REST API endpoint to provide update access to CoffeeMaker's singleton
     * Inventory. This will update the Inventory of the CoffeeMaker by adding
     * amounts from the Inventory provided to the CoffeeMaker's stored inventory
     *
     * @param inventory
     *            amounts to add to inventory
     * @return response to the request
     */
    @CrossOrigin ( origins = "http://localhost:4200" )
    @PutMapping ( BASE_PATH + "/inventory" )
    public ResponseEntity updateInventory ( @RequestBody final Inventory inventory ) {
        final Inventory inventoryCurrent = service.getInventory();

        // check if inventory is valid
        if ( !inventory.validateInventory() ) {
            return new ResponseEntity( errorResponse( "Invalid amount for ingredient" ), HttpStatus.NOT_ACCEPTABLE );
        }

        // clear ingredients from list in case they changed
        inventoryCurrent.clearIngredients();
        final List<Ingredient> list = inventory.getIngredients();

        // adds all the elements to the inventory to make sure they are not
        // duplicates.
        for ( int i = 0; i < list.size(); i++ ) {
            if ( !inventoryCurrent.addIngredient( list.get( i ) ) ) {
                return new ResponseEntity( errorResponse( "Duplicate" ), HttpStatus.CONFLICT );

            }
        }

        // saves the new ingredient data to DB
        service.save( inventoryCurrent );

        return new ResponseEntity( successResponse( "Updated Successfully" ), HttpStatus.OK );
    }

}
