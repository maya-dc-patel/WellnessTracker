package edu.ncsu.csc.WellnessTracker.models;

import java.io.Serializable;
import java.sql.Clob;
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
public class Tea extends DomainObject {

    @Id
    @GeneratedValue
    public Long                    id;
    // @Enumerated ( EnumType.STRING )
    // private IngredientType ingredient;

    private String                 name;
    // TODO make a minimum value??

    private String[] benefits;
    private String[] ingredients;
    

    // @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    // private  List<Ingredient> ingredients;



    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getBenefits() {
        return benefits;
    }

    public void setBenefits(String[] benefits) {
        this.benefits = benefits;
    }

    // public List<Ingredient> getIngredients() {
    //     return ingredients;
    // }

    // public void addIngredient(Ingredient ingredient) {
    //     this.ingredients.add(ingredient);
    // }




    public Tea ( final String name, final String[] ingredients, final String[] benefits) {

        super();
        this.name = name;
  
        this.ingredients = ingredients;
        this.benefits = benefits;
    }

    public Tea () {
        super();
    }


    @Override
    public Serializable getId() {
        return id;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }



   

}
