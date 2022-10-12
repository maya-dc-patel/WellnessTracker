package edu.ncsu.csc.WellnessTracker.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.ncsu.csc.WellnessTracker.common.TestUtils;
import edu.ncsu.csc.WellnessTracker.models.Ingredient;
import edu.ncsu.csc.WellnessTracker.models.Recipe;
import edu.ncsu.csc.WellnessTracker.services.RecipeService;

@RunWith ( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
public class APIRecipeTest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RecipeService         service;

    /**
     * Sets up the tests.
     */
    @Before
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();
        service.deleteAll();
    }

    @Test
    @Transactional
    public void ensureRecipe () throws Exception {
        service.deleteAll();

        final Recipe r = new Recipe();
        // r.setChocolate( 5 );
        // r.setCoffee( 3 );
        // r.setMilk( 4 );
        // r.setSugar( 8 );
        r.setPrice( 10 );
        r.setName( "Mocha" );
        r.addIngredient( new Ingredient( "Sugar", 4 ) );

        mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( r ) ) ).andExpect( status().isOk() );

    }

    @Test
    @Transactional
    public void testRecipeAPI () throws Exception {

        service.deleteAll();

        final Recipe recipe = new Recipe();
        recipe.setName( "Coffee1" );
        // recipe.setChocolate( 10 );
        // recipe.setMilk( 20 );
        // recipe.setSugar( 5 );
        // recipe.setCoffee( 1 );

        recipe.setPrice( 5 );
        recipe.addIngredient( new Ingredient( "Sugar", 4 ) );

        mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( recipe ) ) );

        Assert.assertEquals( 1, (int) service.count() );

    }

    @Test
    @Transactional
    public void testAddRecipe2 () throws Exception {

        /* Tests a recipe with a duplicate name to make sure it's rejected */

        Assert.assertEquals( "There should be no Recipes in the CoffeeMaker", 0, service.findAll().size() );
        final String name = "Coffee";
        final Recipe r1 = createRecipe( name, 50 );

        service.save( r1 );

        final Recipe r2 = createRecipe( name, 50 );
        mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( r2 ) ) ).andExpect( status().is4xxClientError() );

        Assert.assertEquals( "There should only one recipe in the CoffeeMaker", 1, service.findAll().size() );
    }

    @Test
    @Transactional
    public void testAddRecipe15 () throws Exception {

        /* Tests to make sure that our cap of 3 recipes is enforced */

        Assert.assertEquals( "There should be no Recipes in the CoffeeMaker", 0, service.findAll().size() );

        final Recipe r1 = createRecipe( "Coffee", 50 );
        service.save( r1 );
        final Recipe r2 = createRecipe( "Mocha", 50 );
        service.save( r2 );
        final Recipe r3 = createRecipe( "Latte", 60 );
        service.save( r3 );

        Assert.assertEquals( "Creating three recipes should result in three recipes in the database", 3,
                service.count() );

        final Recipe r4 = createRecipe( "Hot Chocolate", 75 );

        mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( r4 ) ) ).andExpect( status().isInsufficientStorage() );

        Assert.assertEquals( "Creating a fourth recipe should not get saved", 3, service.count() );
    }

    /**
     * Checks the error handling for POST call in createRecipe
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void testErrorHandling () throws Exception {

        Assert.assertEquals( "There should be no Recipes in the CoffeeMaker", 0, service.findAll().size() );

        final Recipe r1 = createRecipe( "Coffee", 50 );
        // no ingredients
        MvcResult errorMessage = mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( r1 ) ) ).andExpect( status().isIAmATeapot() ).andReturn();

        Assert.assertTrue( errorMessage.getResponse().getContentAsString().contains( "No ingredients in Coffee" ) );

        r1.addIngredient( new Ingredient( "Mocha", 5 ) );
        // negative price
        r1.setPrice( -2 );
        errorMessage = mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( r1 ) ) ).andExpect( status().isNotAcceptable() ).andReturn();

        Assert.assertTrue( errorMessage.getResponse().getContentAsString()
                .contains( "Invalid price - must be a postivie integer." ) );

        r1.addIngredient( new Ingredient( "Chocolate", -5 ) );
        r1.setPrice( 4 );
        errorMessage = mvc
                .perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                        .content( TestUtils.asJsonString( r1 ) ) )
                .andExpect( status().isAlreadyReported() ).andReturn();

        Assert.assertTrue( errorMessage.getResponse().getContentAsString()
                .contains( "Ingredient  amount is too low for Coffee" ) );

        // final Recipe r2 = createRecipe( "Coffee", 50 );
        //
        // final MvcResult errorMessage2 = mvc.perform( post( "/api/v1/recipes"
        // ).contentType( MediaType.APPLICATION_JSON )
        // .content( TestUtils.asJsonString( r2 ) ) ).andExpect(
        // status().isConflict() ).andReturn();
        //
        // Assert.assertTrue(
        // errorMessage2.getResponse().getContentAsString().contains( "Recipe
        // with the name Coffee" ) );

    }

    private Recipe createRecipe ( final String name, final Integer price ) {
        final Recipe recipe = new Recipe();
        recipe.setName( name );
        recipe.setPrice( price );

        return recipe;
    }

}
