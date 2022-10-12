package edu.ncsu.csc.WellnessTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.WellnessTracker.models.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    Entry findByDate ( String date );

}
