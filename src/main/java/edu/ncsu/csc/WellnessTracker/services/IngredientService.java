package edu.ncsu.csc.WellnessTracker.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.WellnessTracker.models.Ingredient;
import edu.ncsu.csc.WellnessTracker.repositories.IngredientRepository;

@Component
@Transactional
public class IngredientService extends Service<Ingredient, Long> {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    protected JpaRepository getRepository () {
        return ingredientRepository;
    }

    public Ingredient findByName ( final String name ) {
        return ingredientRepository.findByIngredient( name );
    }
}
