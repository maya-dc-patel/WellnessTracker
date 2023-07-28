package edu.ncsu.csc.WellnessTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.WellnessTracker.models.Journal;
import edu.ncsu.csc.WellnessTracker.models.Tea;

public interface TeaRepository extends JpaRepository<Tea, Long> {

    Tea findByName ( String name );

}
