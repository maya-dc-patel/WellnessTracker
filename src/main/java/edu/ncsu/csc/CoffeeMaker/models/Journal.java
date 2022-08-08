package edu.ncsu.csc.CoffeeMaker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Journal extends DomainObject {

    @Id
    @GeneratedValue
    public Long                    id;
    // @Enumerated ( EnumType.STRING )
    // private IngredientType ingredient;

    private String                 name;
    // TODO make a minimum value??
    private String                 owner;

    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private final List<Ingredient> ingredients;

    public Journal ( final String name, final String owner ) {

        super();
        this.name = name;
        this.owner = owner;
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Journal () {
        super();
        this.ingredients = new ArrayList<Ingredient>();
    }

    @Override
    public String toString () {
        return "Ingredient [name=" + name + ", owner=" + owner + "ingredients=" + ingredients.toString() + "]";
    }

    public String getName () {
        return name;
    }

    public void setName ( final String name ) {
        this.name = name;
    }

    public String getOwner () {
        return owner;
    }

    public void setOwner ( final String owner ) {
        this.owner = owner;
    }

    public List<Ingredient> getIngredients () {
        return ingredients;
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
        final Journal other = (Journal) obj;
        return ( other.getName() == this.getName() ) && Objects.equals( name, other.name );
    }

}
