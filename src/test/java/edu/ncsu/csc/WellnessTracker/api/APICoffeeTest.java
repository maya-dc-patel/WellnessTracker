package edu.ncsu.csc.WellnessTracker.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import edu.ncsu.csc.WellnessTracker.common.TestUtils;
import edu.ncsu.csc.WellnessTracker.models.Inventory;
import edu.ncsu.csc.WellnessTracker.models.Recipe;
import edu.ncsu.csc.WellnessTracker.services.InventoryService;
import edu.ncsu.csc.WellnessTracker.services.RecipeService;

@RunWith ( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
public class APICoffeeTest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RecipeService         service;

    @Autowired
    private InventoryService      iService;

    /**
     * Sets up the tests.
     */
    @Before
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();

        final Inventory ivt = iService.getInventory();

        // ivt.setChocolate( 15 );
        // ivt.setCoffee( 15 );
        // ivt.setMilk( 15 );
        // ivt.setSugar( 15 );

        iService.save( ivt );

        final Recipe recipe = new Recipe();
        recipe.setName( "Coffee" );
        recipe.setPrice( 50 );
        // recipe.setCoffee( 3 );
        // recipe.setMilk( 1 );
        // recipe.setSugar( 1 );
        // recipe.setChocolate( 0 );
        service.save( recipe );
    }

    @Test
    @Transactional
    public void testPurchaseBeverage1 () throws Exception {

        final String name = "Coffee";

        mvc.perform( post( String.format( "/api/v1/makecoffee/%s", name ) ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( 60 ) ) ).andExpect( status().isOk() )
                .andExpect( jsonPath( "$.message" ).value( 10 ) );

    }

    @Test
    @Transactional
    public void testPurchaseBeverage2 () throws Exception {
        /* Insufficient amount paid */

        final String name = "Coffee";

        mvc.perform( post( String.format( "/api/v1/makecoffee/%s", name ) ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( 40 ) ) ).andExpect( status().is4xxClientError() )
                .andExpect( jsonPath( "$.message" ).value( "Not enough money paid" ) );

    }

    // @Test
    // @Transactional
    // public void testPurchaseBeverage3 () throws Exception {
    // /* Insufficient inventory */
    //
    // final Inventory ivt = iService.getInventory();
    // ivt.setCoffee( 0 );
    // iService.save( ivt );
    //
    // final String name = "Coffee";
    //
    // mvc.perform( post( String.format( "/api/v1/makecoffee/%s", name )
    // ).contentType( MediaType.APPLICATION_JSON )
    // .content( TestUtils.asJsonString( 50 ) ) ).andExpect(
    // status().is4xxClientError() )
    // .andExpect( jsonPath( "$.message" ).value( "Not enough inventory" ) );
    //
    // }

}
