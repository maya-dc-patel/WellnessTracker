package edu.ncsu.csc.CoffeeMaker.api;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.ncsu.csc.CoffeeMaker.common.TestUtils;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.models.Inventory;
import edu.ncsu.csc.CoffeeMaker.models.Recipe;

@RunWith ( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc

public class APITest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    /**
     * Sets up the tests.
     */
    @Before
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();
    }

    @Test
    @Transactional
    public void correlateRecipe () throws Exception {
        String recipe = mvc.perform( get( "/api/v1/recipes" ) ).andDo( print() ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        if ( !recipe.contains( "Mocha" ) ) {
            // Create new Mocha recipe
            final Recipe r = new Recipe();
            // r.setChocolate( 5 );
            // r.setCoffee( 3 );
            // r.setMilk( 4 );
            // r.setSugar( 8 );
            r.setPrice( 10 );
            r.setName( "Mocha" );
            r.addIngredient( new Ingredient( "Sugar", 4 ) );

            // Add recipe
            mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                    .content( TestUtils.asJsonString( r ) ) ).andExpect( status().isOk() );
        }

        // Make sure recipe was created, pull it from database
        recipe = mvc.perform( get( "/api/v1/recipes" ) ).andDo( print() ).andExpect( status().isOk() ).andReturn()
                .getResponse().getContentAsString();

        // Make sure that the recipe we just instructed the server to create is
        // actually there
        assertTrue( recipe.contains( "Mocha" ) );

        // Update the inventory
        final Inventory inven = new Inventory();
        inven.addIngredient( new Ingredient( "Coffee", 5 ) );

        mvc.perform( put( "/api/v1/inventory" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( inven ) ) ).andExpect( status().isOk() );

        // Make the coffee and pay for it
        mvc.perform( post( "/api/v1/makecoffee/Mocha" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( 100 ) ) ).andExpect( status().isOk() );

        // Delete recipe
        final String delete = mvc.perform( delete( "/api/v1/recipes/Mocha" ) ).andDo( print() )
                .andExpect( status().isOk() ).andReturn().getResponse().getContentAsString();

        assertTrue( delete.contains( "Mocha was deleted successfully" ) );
    }

    @Test
    @Transactional
    public void testGetInventory () throws UnsupportedEncodingException, Exception {

        // Updating inventory
        final Inventory inventory = new Inventory();
        inventory.addIngredient( new Ingredient( "Coffee", 5 ) );
        mvc.perform( put( "/api/v1/inventory" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( inventory ) ) ).andExpect( status().isOk() );

        // Retrieve inventory
        final String inven = mvc.perform( get( "/api/v1/inventory" ) ).andDo( print() ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        // Make sure it has ingredient
        assertTrue( inven.contains( "Coffee" ) );
    }

    // @Test
    // public void invenTest () throws Exception {
    // // Update the inventory
    // final Inventory inven = new Inventory();
    // inven.addIngredient( new Ingredient( "Coffee", 5 ) );
    // inven.addIngredient( new Ingredient( "Milk", 3 ) );
    // inven.addIngredient( new Ingredient( "Caramel", 6 ) );
    //
    // mvc.perform( put( "/api/v1/inventory" ).contentType(
    // MediaType.APPLICATION_JSON )
    // .content( TestUtils.asJsonString( inven ) ) ).andExpect( status().isOk()
    // );
    // }

}
