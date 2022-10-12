package edu.ncsu.csc.WellnessTracker.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.WellnessTracker.models.Journal;
import edu.ncsu.csc.WellnessTracker.repositories.JournalRepository;

@Component
@Transactional
public class JournalService extends Service<Journal, Long> {

    @Autowired
    private JournalRepository ingredientRepository;

    @Override
    protected JpaRepository getRepository () {
        return ingredientRepository;
    }

    public Journal findByName ( final String name ) {
        return ingredientRepository.findByName( name );
    }
}
