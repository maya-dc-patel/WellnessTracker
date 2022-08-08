package edu.ncsu.csc.CoffeeMaker.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.CoffeeMaker.models.Journal;
import edu.ncsu.csc.CoffeeMaker.repositories.JournalRepository;

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
