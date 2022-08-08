package edu.ncsu.csc.CoffeeMaker.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.TestConfig;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.models.Journal;
import edu.ncsu.csc.CoffeeMaker.models.Recipe;
import edu.ncsu.csc.CoffeeMaker.services.JournalService;
import edu.ncsu.csc.CoffeeMaker.services.RecipeService;

@RunWith ( SpringRunner.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class RecipeTest {

    @Autowired
    private RecipeService  service;
    @Autowired
    private JournalService journalService;

    @Before
    public void setup () {
        service.deleteAll();
        journalService.deleteAll();
    }

    @Test
    @Transactional
    public void testAddRecipe () {

        final Recipe r1 = new Recipe();
        r1.setName( "Black Coffee" );
        r1.setPrice( 1 );
        // r1.setCoffee( 1 );
        // r1.setMilk( 0 );
        // r1.setSugar( 0 );
        // r1.setChocolate( 0 );
        service.save( r1 );

        final Recipe r2 = new Recipe();
        r2.setName( "Mocha" );
        r2.setPrice( 1 );
        // r2.setCoffee( 1 );
        // r2.setMilk( 1 );
        // r2.setSugar( 1 );
        // r2.setChocolate( 1 );
        service.save( r2 );

        final List<Recipe> recipes = service.findAll();
        Assert.assertEquals( "Creating two recipes should result in two recipes in the database", 2, recipes.size() );

        Assert.assertEquals( "The retrieved recipe should match the created one", r1, recipes.get( 0 ) );
    }

    @Test
    @Transactional
    public void testNoRecipes () {
        Assert.assertEquals( "There should be no Recipes in the CoffeeMaker", 0, service.findAll().size() );

        final Recipe r1 = new Recipe();
        r1.setName( "Tasty Drink" );
        r1.setPrice( -12 );
        // r1.setCoffee( -12 );
        // r1.setMilk( 0 );
        // r1.setSugar( 0 );
        // r1.setChocolate( 0 );

        final Recipe r2 = new Recipe();
        r2.setName( "Mocha" );
        r2.setPrice( 1 );
        // r2.setCoffee( 1 );
        // r2.setMilk( 1 );
        // r2.setSugar( 1 );
        // r2.setChocolate( 1 );

        final List<Recipe> recipes = List.of( r1, r2 );

        try {
            service.saveAll( recipes );
            Assert.assertEquals(
                    "Trying to save a collection of elements where one is invalid should result in neither getting saved",
                    0, service.count() );
        }
        catch ( final Exception e ) {
            Assert.assertTrue( e instanceof ConstraintViolationException );
        }

    }

    @Test
    @Transactional
    public void testEquals () {
        final Recipe r1 = new Recipe();

        r1.setName( "Mocha" );
        r1.setPrice( 2 );

        Recipe r2 = null;

        // Check null check
        assertFalse( r1.equals( r2 ) );

        // Check class check
        assertFalse( r1.equals( "Hello" ) );

        // See if it identifies correctly
        r2 = new Recipe();
        r2.setName( "Mocha" );
        r2.setPrice( 2 );
        assertTrue( r1.equals( r2 ) );

    }

    @Test
    @Transactional
    public void testIngredientEquals () {
        // Create two ingredients
        final Ingredient ing1 = new Ingredient( "Milk", 4 );
        Ingredient ing2 = null;

        // Make sure it passes null test
        assertFalse( ing1.equals( ing2 ) );

        // Make sure it identifies them as same with same name
        ing2 = new Ingredient( "Milk", 4 );
        assertTrue( ing1.equals( ing2 ) );

    }

    @Test
    @Transactional
    public void testValidateRecipe () {
        // Create two ingredients
        final Recipe r1 = new Recipe();

        r1.setName( "Mocha" );
        r1.setPrice( -2 );

        r1.addIngredient( new Ingredient( "Mocha", 5 ) );

        // See if it identifies correctly
        assertEquals( 2, r1.validateRecipe() );
        r1.addIngredient( new Ingredient( "Coff", -5 ) );
        r1.setPrice( 2 );
        assertEquals( 4, r1.validateRecipe() );

    }

    @Test
    @Transactional
    public void testToString () {
        // Create two ingredients
        final Recipe r1 = new Recipe();

        r1.setName( "Mocha" );
        r1.setPrice( -2 );

        r1.addIngredient( new Ingredient( "Mocha", 5 ) );

        // See if it identifies correctly

        assertEquals( "Mocha with ingredients [Ingredient [ingredient=Mocha, amount=5]]", r1.toString() );

    }

    @Test
    public void testJournalClass () {
        // Create two ingredients
        final Journal j = new Journal( "Notebook 1", "Maya" );
        journalService.save( j );

    }

}
