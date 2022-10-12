package edu.ncsu.csc.WellnessTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.WellnessTracker.models.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

    Journal findByName ( String name );

}
