package edu.ncsu.csc.CoffeeMaker.datageneration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ncsu.csc.CoffeeMaker.TestConfig;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.models.Inventory;
import edu.ncsu.csc.CoffeeMaker.services.IngredientService;
import edu.ncsu.csc.CoffeeMaker.services.InventoryService;
import edu.ncsu.csc.CoffeeMaker.services.RecipeService;

@RunWith ( SpringRunner.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class GenerateIngredients {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private InventoryService  inventoryService;

    @Autowired
    private RecipeService     recipeService;

    // @Test
    //
    // public void testCreateIngredients () {
    // // recipeService.deleteAll();
    // // ingredientService.deleteAll();
    // // Assert.assertEquals( 0, ingredientService.count() );
    // final Ingredient i1 = new Ingredient( "COFFEE", 5 );
    // ingredientService.save( i1 );
    // final Ingredient i2 = new Ingredient( "MILK", 3 );
    // ingredientService.save( i2 );
    //
    // // Assert.assertEquals( 2, ingredientService.count() );
    //
    // // ingredientService.deleteAll();
    // // Assert.assertEquals( 0, ingredientService.count() );
    //
    // }

    // public void testCreateIngredients () {
    // // recipeService.deleteAll();
    // // ingredientService.deleteAll();
    // // Assert.assertEquals( 0, ingredientService.count() );
    // final Ingredient i1 = new Ingredient( "COFFEE", 5 );
    //
    // ingredientService.save( i1 );
    //
    // final Ingredient i2 = new Ingredient( "MILK", 3 );
    //
    // ingredientService.save( i2 );
    //
    // // Assert.assertEquals( 2, ingredientService.count() );
    //
    // // ingredientService.deleteAll();
    // // Assert.assertEquals( 0, ingredientService.count() );
    //
    // }

    @Test

    public void testAddIngredientsToInventory () {
        // ingredientService.deleteAll();
        // inventoryService.deleteAll();
        // recipeService.deleteAll();
        final Inventory inv = new Inventory();
        final Ingredient i1 = new Ingredient( "COFFEE", 5 );
        // Assert.assertEquals( 0, inv.count() );

        inv.addIngredient( i1 );
        // Assert.assertEquals( 1, inv.count() );
        // Assert.assertEquals( 0, inventoryService.count() );

        inventoryService.save( inv );

        // Assert.assertEquals( 1, inventoryService.count() );
        // check if deletes
        inv.addIngredient( new Ingredient( "milk", 2 ) );
        inventoryService.save( inv );
        // Assert.assertEquals( 1, inventoryService.count() );

        // inventoryService.deleteAll();
        // inventoryService.delete( inv );
        // Assert.assertEquals( 0, inventoryService.count() );

        // ingredientService.save( i1 );
        //
        // final Ingredient i2 = new Ingredient( "MILK", 3 );
        //
        // ingredientService.save( i2 );
        //
        // Assert.assertEquals( 2, ingredientService.count() );

    }

    // @Test
    //
    // public void testRecipe () {
    // recipeService.deleteAll();
    // ingredientService.deleteAll();
    // inventoryService.deleteAll();
    // final Inventory inv = new Inventory();
    // final Ingredient i1 = new Ingredient( "COFFEE", 5 );
    // final Recipe r = new Recipe();
    // r.setName( "recipe 1" );
    // r.setPrice( 2 );
    // r.addIngredient( i1 );
    // // Assert.assertEquals( 0, recipeService.count() );
    // recipeService.save( r );
    // // Assert.assertEquals( 1, recipeService.count() );
    // r.addIngredient( new Ingredient( "milk", 6 ) );
    // recipeService.save( r );
    // // Assert.assertEquals( 1, recipeService.count() );
    //
    // // Assert.assertEquals( 0, inv.count() );
    //
    // inv.addIngredient( i1 );
    // // Assert.assertEquals( 1, inv.count() );
    // // Assert.assertEquals( 0, inventoryService.count() );
    //
    // inventoryService.save( inv );
    // // Assert.assertEquals( 1, inventoryService.count() );
    // // check if deletes
    // inv.addIngredient( new Ingredient( "milk", 2 ) );
    // inventoryService.save( inv );
    // // Assert.assertEquals( 1, inventoryService.count() );
    //
    // inventoryService.deleteAll();
    // inventoryService.delete( inv );
    // // Assert.assertEquals( 0, inventoryService.count() );
    //
    // // ingredientService.save( i1 );
    // //
    // // final Ingredient i2 = new Ingredient( "MILK", 3 );
    // //
    // // ingredientService.save( i2 );
    // //
    // // Assert.assertEquals( 2, ingredientService.count() );
    //
    // }
}
