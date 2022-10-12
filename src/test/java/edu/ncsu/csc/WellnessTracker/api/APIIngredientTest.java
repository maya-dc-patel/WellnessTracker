// package edu.ncsu.csc.CoffeeMaker.api;
//
// import static org.junit.Assert.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import javax.transaction.Transactional;
//
// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
//
// import edu.ncsu.csc.CoffeeMaker.common.TestUtils;
// import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
// import edu.ncsu.csc.CoffeeMaker.services.IngredientService;
// import edu.ncsu.csc.CoffeeMaker.services.RecipeService;
//
// @RunWith ( SpringRunner.class )
// @SpringBootTest
// @AutoConfigureMockMvc
// public class APIIngredientTest {
//
// /**
// * MockMvc uses Spring's testing framework to handle requests to the REST
// * API
// */
// private MockMvc mvc;
//
// @Autowired
// private WebApplicationContext context;
//
// @Autowired
// private IngredientService service;
// @Autowired
// private RecipeService recipeService;
//
// /**
// * Sets up the tests.
// */
// @Before
// public void setup () {
// mvc = MockMvcBuilders.webAppContextSetup( context ).build();
// }
//
// @Test
//
// public void addIngredient () throws Exception {
// recipeService.deleteAll();
// service.deleteAll();
//
// final Ingredient i = new Ingredient( "COFFEE", 5 );
// System.out.println( TestUtils.asJsonString( i ) );
// // mvc.perform( post( "/api/v1/ingredients" ).contentType(
// // MediaType.APPLICATION_JSON )
// // .content( TestUtils.asJsonString( i ) ) ).andExpect( status().isOk()
// // );
// mvc.perform( post( "/api/v1/ingredients" ).contentType(
// MediaType.APPLICATION_JSON )
// .content( TestUtils.asJsonString( i ) ) ).andExpect( status().isCreated() );
//
// // Assert.assertEquals( 1, (int) service.count() );
// service.deleteAll();
// // assertEquals( 0, service.count() );
// }
//
// @Test
//
// public void updateIngredient () throws Exception {
// recipeService.deleteAll();
//
// service.deleteAll();
// service.count();
// // Assert.assertEquals( 0, (int) service.count() );
//
// final Ingredient i = new Ingredient( "COFFEE", 5 );
// System.out.println( TestUtils.asJsonString( i ) );
//
// mvc.perform( post( "/api/v1/ingredients" ).contentType(
// MediaType.APPLICATION_JSON )
// .content( TestUtils.asJsonString( i ) ) ).andExpect( status().isCreated() );
//
// // Assert.assertEquals( 1, (int) service.count() );
//
// final Ingredient newIng = new Ingredient( "COFFEE", 4 );
//
// mvc.perform( put( "/api/v1/ingredients/COFFEE" ).contentType(
// MediaType.APPLICATION_JSON )
// .content( TestUtils.asJsonString( newIng ) ) ).andExpect( status().isOk() );
//
// }
//
// // @Test
// // @Transactional
// // public void deleteIngredient () throws Exception {
// // recipeService.deleteAll();
// //
// // service.deleteAll();
// // final Ingredient i = new Ingredient( "COFFEE", 5 );
// // // System.out.println( TestUtils.asJsonString( i ) );
// //
// // mvc.perform( post( "/api/v1/ingredients" ).contentType(
// // MediaType.APPLICATION_JSON )
// // .content( TestUtils.asJsonString( i ) ) );
// //
// // // Assert.assertEquals( 1, (int) service.count() );
// //
// // final String ing = mvc.perform( get( "/api/v1/ingredients/COFFEE" )
// // ).andDo( print() )
// // .andExpect( status().isOk()
// // ).andReturn().getResponse().getContentAsString();
// //
// // // System.out.println( "HERE: " + ing );
// //
// // // final String delete = mvc.perform( delete(
// // // "/api/v1/ingredients/COFFEE" ) ).andDo( print() )
// // // .andExpect( status().isOk()
// // // ).andReturn().getResponse().getContentAsString();
// //
// // // Assert.assertEquals( 0, (int) service.count() );
// //
// // // assertTrue( delete.contains( "COFFEE was deleted successfully" ) );
//
// }
//
// @Test
// @Transactional
// public void getIngredient () throws Exception {
// recipeService.deleteAll();
// service.deleteAll();
//
// final Ingredient i = new Ingredient( "COFFEE", 5 );
// // System.out.println( TestUtils.asJsonString( i ) );
//
// mvc.perform( post( "/api/v1/ingredients" ).contentType(
// MediaType.APPLICATION_JSON )
// .content( TestUtils.asJsonString( i ) ) ).andExpect( status().isCreated() );
//
// Assert.assertEquals( 1, (int) service.count() );
//
// final String ing = mvc.perform( get( "/api/v1/ingredients/COFFEE" ) ).andDo(
// print() )
// .andExpect( status().isOk() ).andReturn().getResponse().getContentAsString();
//
// assertTrue( ing.contains( "COFFEE" ) );
// service.deleteAll();
// assertEquals( 0, service.count() );
//
// }
//
// }
