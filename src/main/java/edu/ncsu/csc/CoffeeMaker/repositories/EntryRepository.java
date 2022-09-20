package edu.ncsu.csc.CoffeeMaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.CoffeeMaker.models.Entry;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    Entry findByDate ( String date );

}
