package edu.ncsu.csc.CoffeeMaker.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Inventory for the coffee maker. Inventory is tied to the database using
 * Hibernate libraries. See InventoryRepository and InventoryService for the
 * other two pieces used for database support.
 *
 * @author Kai Presler-Marshall
 */
@Entity
public class Inventory extends DomainObject {

    /** id for inventory entry */
    @Id
    @GeneratedValue
    private Long             id;
    // /** amount of coffee */
    // @Min ( 0 )
    // private Integer coffee;
    // /** amount of milk */
    // @Min ( 0 )
    // private Integer milk;
    // /** amount of sugar */
    // @Min ( 0 )
    // private Integer sugar;
    // /** amount of chocolate */
    // @Min ( 0 )
    // private Integer chocolate;
    /** List of ingredients */
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Ingredient> ingredients;

    /**
     * Empty constructor for Hibernate
     */
    public Inventory () {
        this.ingredients = new ArrayList<Ingredient>();
        // Intentionally empty so that Hibernate can instantiate
        // Inventory object.
    }

    // /**
    // * Use this to create inventory with specified amts.
    // *
    // * @param coffee
    // * amt of coffee
    // * @param milk
    // * amt of milk
    // * @param sugar
    // * amt of sugar
    // * @param chocolate
    // * amt of chocolate
    // */
    // public Inventory ( final Integer coffee, final Integer milk, final
    // Integer sugar, final Integer chocolate ) {
    // this.ingredients = new ArrayList<Ingredient>();
    // setCoffee( coffee );
    // setMilk( milk );
    // setSugar( sugar );
    // setChocolate( chocolate );
    // }

    /**
     * Returns the ID of the entry in the DB
     *
     * @return long
     */
    @Override
    public Long getId () {
        return id;
    }

    /**
     * Set the ID of the Inventory (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    public void setId ( final Long id ) {
        this.id = id;
    }

    // /**
    // * Returns the current number of chocolate units in the inventory.
    // *
    // * @return amount of chocolate
    // */
    // public Integer getChocolate () {
    // return chocolate;
    // }

    // /**
    // * Sets the number of chocolate units in the inventory to the specified
    // * amount.
    // *
    // * @param amtChocolate
    // * amount of chocolate to set
    // */
    // public void setChocolate ( final Integer amtChocolate ) {
    // if ( amtChocolate >= 0 ) {
    // chocolate = amtChocolate;
    // }
    // }

    /**
     * Returns the entire list of ingredients
     *
     */
    public List<Ingredient> getIngredients () {
        return ingredients;
    }

    public int getAmount ( final String ingredient ) {
        for ( int i = 0; i < ingredients.size(); i++ ) {
            if ( ingredients.get( i ).getIngredient().equals( ingredient ) ) {
                return ingredients.get( i ).getAmount();
            }
        }
        return -1;
    }

    /**
     * Returns true if there are enough ingredients to make the beverage.
     *
     * @param r
     *            recipe to check if there are enough ingredients
     * @return true if enough ingredients to make the beverage
     */
    public boolean enoughIngredients ( final Recipe r ) {
        final boolean isEnough = true;
        // if ( coffee < r.getCoffee() ) {
        // isEnough = false;
        // }
        // if ( milk < r.getMilk() ) {
        // isEnough = false;
        // }
        // if ( sugar < r.getSugar() ) {
        // isEnough = false;
        // }
        // if ( chocolate < r.getChocolate() ) {
        // isEnough = false;
        // }
        final List<Ingredient> list = r.getIngredients();

        return isEnough;
    }

    // public int getAmount ( final String name ) {
    //
    // }

    /**
     * Removes the ingredients used to make the specified recipe. Assumes that
     * the user has checked that there are enough ingredients to make
     *
     * @param r
     *            recipe to make
     * @return true if recipe is made.
     */
    public boolean useIngredients ( final Recipe r ) {
        if ( enoughIngredients( r ) ) {
            // setCoffee( coffee - r.getCoffee() );
            // setMilk( milk - r.getMilk() );
            // setSugar( sugar - r.getSugar() );
            // setChocolate( chocolate - r.getChocolate() );
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds ingredients to the inventory
     *
     * @param coffee
     *            amt of coffee
     * @param milk
     *            amt of milk
     * @param sugar
     *            amt of sugar
     * @param chocolate
     *            amt of chocolate
     * @return true if successful, false if not
     */
    public boolean setIngredient ( final String name, final Integer amount ) {
        if ( amount < 0 ) {
            throw new IllegalArgumentException( "Amount cannot be negative" );
        }
        boolean found = false;
        for ( int i = 0; i < ingredients.size(); i++ ) {
            if ( ingredients.get( i ).getIngredient().equals( name ) ) {
                ingredients.get( i ).setAmount( amount );
                found = true;
            }
        }
        return found;

    }

    /**
     * Adds ingredient to list
     *
     * @return true if successful
     */
    public boolean addIngredient ( final Ingredient ingredient ) {

        for ( final Ingredient i : ingredients ) {
            if ( ingredient.getIngredient().equals( i.getIngredient() ) ) {
                return false;
            }

        }

        return ingredients.add( ingredient );

    }

    public void clearIngredients () {
        this.ingredients = new ArrayList<Ingredient>();

    }

    /**
     * This method validates inventory ingredients
     *
     * @return true if valid, false if not
     */
    public boolean validateInventory () {

        for ( final Ingredient i : ingredients ) {
            if ( i.getAmount() < 0 ) {
                // System.out.println( "amount too low" );
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a string describing the current contents of the inventory.
     *
     * @return String
     */
    @Override
    public String toString () {
        final StringBuffer buf = new StringBuffer();
        // buf.append( "Coffee: " );
        // buf.append( getCoffee() );
        // buf.append( "\n" );
        // buf.append( "Milk: " );
        // buf.append( getMilk() );
        // buf.append( "\n" );
        // buf.append( "Sugar: " );
        // buf.append( getSugar() );
        // buf.append( "\n" );
        // buf.append( "Chocolate: " );
        // buf.append( getChocolate() );
        // buf.append( "\n" );
        for ( int i = 0; i < ingredients.size(); i++ ) {
            if ( i != 0 ) {
                buf.append( "\n" );

            }
            buf.append( ingredients.get( i ).getIngredient() + ": " + ingredients.get( i ).getAmount() );
        }
        return buf.toString() + "\n";
    }

}
