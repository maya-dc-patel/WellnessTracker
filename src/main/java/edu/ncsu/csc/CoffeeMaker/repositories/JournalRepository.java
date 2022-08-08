package edu.ncsu.csc.CoffeeMaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.CoffeeMaker.models.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

    Journal findByName ( String name );

}
