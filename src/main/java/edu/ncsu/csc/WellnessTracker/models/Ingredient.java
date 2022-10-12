package edu.ncsu.csc.WellnessTracker.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient extends DomainObject {

    @Id
    @GeneratedValue
    public Long    id;
    // @Enumerated ( EnumType.STRING )
    // private IngredientType ingredient;

    private String ingredient;
    // TODO make a minimum value??
    private int    amount;

    public Ingredient ( final String ingredient, final int amount ) {
        super();
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient () {
        super();
    }

    @Override
    public String toString () {
        return "Ingredient [ingredient=" + ingredient + ", amount=" + amount + "]";
    }

    public String getIngredient () {
        return ingredient;
    }

    public void setIngredient ( final String ingredient ) {
        this.ingredient = ingredient;
    }

    public Integer getAmount () {
        return amount;
    }

    public void setAmount ( final Integer amount ) {
        this.amount = amount;
    }

    @SuppressWarnings ( "unused" )
    private void setId ( final Long id ) {
        this.id = id;
    }

    @Override
    public Serializable getId () {
        return id;
    }

    @Override
    public boolean equals ( final Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        return ( other.getAmount() == this.getAmount() ) && Objects.equals( ingredient, other.ingredient );
    }

}
