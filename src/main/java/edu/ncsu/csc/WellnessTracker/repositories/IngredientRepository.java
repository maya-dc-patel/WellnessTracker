package edu.ncsu.csc.WellnessTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.WellnessTracker.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByIngredient ( String name );

}
