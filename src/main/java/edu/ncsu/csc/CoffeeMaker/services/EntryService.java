package edu.ncsu.csc.CoffeeMaker.services;

import edu.ncsu.csc.CoffeeMaker.models.Entry;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.repositories.EntryRepository;
import edu.ncsu.csc.CoffeeMaker.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class EntryService extends Service<Entry, Long> {

    @Autowired
    private EntryRepository entryRepository;

    @Override
    protected JpaRepository getRepository () {
        return entryRepository;
    }

    public Entry findByDate ( final String date ) {
        return entryRepository.findByDate( date );
    }
}
