package edu.ncsu.csc.WellnessTracker.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.WellnessTracker.TestConfig;
import edu.ncsu.csc.WellnessTracker.models.Ingredient;
import edu.ncsu.csc.WellnessTracker.models.Inventory;
import edu.ncsu.csc.WellnessTracker.services.InventoryService;

@RunWith ( SpringRunner.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class InventoryTest {

    @Autowired
    private InventoryService inventoryService;

    @Before
    public void setup () {
        inventoryService.deleteAll();
        final Inventory ivt = inventoryService.getInventory();

        ivt.addIngredient( new Ingredient( "Coffee", 500 ) );
        ivt.addIngredient( new Ingredient( "Milk", 500 ) );
        ivt.addIngredient( new Ingredient( "Sugar", 500 ) );
        ivt.addIngredient( new Ingredient( "Chocolate", 500 ) );

        inventoryService.save( ivt );
    }

    @Test
    @Transactional
    public void testSetIdGetAmtAddTo () {
        final Inventory ivt = inventoryService.getInventory();
        assertNotNull( ivt.getId() );
        ivt.setId( (long) 600 );
        assertEquals( 600, ivt.getId() );

        // Check get amount
        assertEquals( 500, ivt.getAmount( "Chocolate" ) );

        // Check add to
        ivt.setIngredient( "Chocolate", 3 );
        assertEquals( 3, ivt.getAmount( "Chocolate" ) );

        // Check add to with invalid amount
        try {
            ivt.setIngredient( "Chocolate", -3 );
        }
        catch ( final Exception e ) {
            assertEquals( e.getMessage(), "Amount cannot be negative" );
        }

    }

    @Test
    @Transactional
    public void testToString () {

        final Inventory ivt = inventoryService.getInventory();

        assertEquals( "Coffee: 500\nMilk: 500\nSugar: 500\nChocolate: 500\n", ivt.toString() );
    }

    @Test
    @Transactional
    public void testIngredientService () {

        final Inventory ivt = inventoryService.getInventory();

        assertEquals( "Coffee: 500\nMilk: 500\nSugar: 500\nChocolate: 500\n", ivt.toString() );

    }

}
