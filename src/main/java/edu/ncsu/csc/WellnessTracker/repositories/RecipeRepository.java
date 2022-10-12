package edu.ncsu.csc.WellnessTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.WellnessTracker.models.Recipe;

/**
 * RecipeRepository is used to provide CRUD operations for the Recipe model.
 * Spring will generate appropriate code with JPA.
 *
 * @author Kai Presler-Marshall
 *
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    /**
     * Finds a Recipe object with the provided name. Spring will generate code
     * to make this happen.
     * 
     * @param name
     *            Name of the recipe
     * @return Found recipe, null if none.
     */
    Recipe findByName ( String name );

}
