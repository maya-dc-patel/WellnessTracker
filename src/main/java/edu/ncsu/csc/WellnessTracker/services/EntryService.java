package edu.ncsu.csc.WellnessTracker.services;

import edu.ncsu.csc.WellnessTracker.models.Entry;
import edu.ncsu.csc.WellnessTracker.repositories.EntryRepository;
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
