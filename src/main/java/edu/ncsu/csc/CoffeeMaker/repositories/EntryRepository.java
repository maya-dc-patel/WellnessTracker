package edu.ncsu.csc.CoffeeMaker.repositories;

import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findByDate ( String date );

}
