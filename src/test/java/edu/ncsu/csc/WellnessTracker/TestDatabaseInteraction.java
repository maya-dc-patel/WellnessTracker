package edu.ncsu.csc.WellnessTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ncsu.csc.WellnessTracker.models.Ingredient;
import edu.ncsu.csc.WellnessTracker.models.Recipe;
import edu.ncsu.csc.WellnessTracker.models.Tea;
import edu.ncsu.csc.WellnessTracker.services.JournalService;
import edu.ncsu.csc.WellnessTracker.services.RecipeService;
import edu.ncsu.csc.WellnessTracker.services.TeaService;

@RunWith ( SpringRunner.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )

public class TestDatabaseInteraction {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private TeaService teaService;

    @Test
    // @Transactional
    public void testRecipes () {
        recipeService.deleteAll();
        final Recipe r = new Recipe();
        r.setName( "Coffee 1" );
        r.setPrice( 1 );
        // r.setChocolate( 1 );
        // r.setMilk( 1 );
        // r.setSugar( 1 );
        // r.setCoffee( 1 );
        recipeService.save( r );
        teaService.deleteAll();
        List<String> ings = new ArrayList<>();
        ings.add("ginger");
        ings.add("aloe");
         Tea t = new Tea();
         t.setName("chamomile");
         String[] list = {"hello", "lemon"};
         t.setIngredients(list);
         final Ingredient i = new Ingredient("ginger", -1);
        //  t.addIngredient(i);
         teaService.save(t);

        

        final List<Recipe> dbRecipes = recipeService.findAll();

        assertEquals( 1, dbRecipes.size() );

        final Recipe dbRecipe = dbRecipes.get( 0 );

        assertEquals( r.getName(), dbRecipe.getName() );
        // assertEquals( r.getChocolate(), dbRecipe.getChocolate() );
        // assertEquals( r.getSugar(), dbRecipe.getSugar() );
        // assertEquals( r.getMilk(), dbRecipe.getMilk() );
        assertEquals( r.getPrice(), dbRecipe.getPrice() );
        // assertEquals( r.getCoffee(), dbRecipe.getCoffee() );

        final Recipe dbRecipe1 = recipeService.findByName( "Coffee 1" );
        assertEquals( r.getName(), dbRecipe1.getName() );
        // assertEquals( r.getChocolate(), dbRecipe1.getChocolate() );
        // assertEquals( r.getSugar(), dbRecipe1.getSugar() );
        // assertEquals( r.getMilk(), dbRecipe1.getMilk() );
        assertEquals( r.getPrice(), dbRecipe1.getPrice() );
        // assertEquals( r.getCoffee(), dbRecipe1.getCoffee() );

        dbRecipe.setPrice( 15 );
        // dbRecipe.setSugar( 12 );
        recipeService.save( dbRecipe );

        assertEquals( r.getPrice(), dbRecipe.getPrice() );
        // assertEquals( r.getSugar(), dbRecipe.getSugar() );
        assertEquals( 1, recipeService.findAll().size() );
        assertEquals( 1, recipeService.count() );
        assertEquals( 15, (int) recipeService.findAll().get( 0 ).getPrice() );

        recipeService.delete( dbRecipe );
        assertEquals( 0, recipeService.findAll().size() );
        assertEquals( 0, recipeService.count() );

    }

}
