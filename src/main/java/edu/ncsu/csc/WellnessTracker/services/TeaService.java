package edu.ncsu.csc.WellnessTracker.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.WellnessTracker.models.Journal;
import edu.ncsu.csc.WellnessTracker.models.Tea;
import edu.ncsu.csc.WellnessTracker.repositories.JournalRepository;
import edu.ncsu.csc.WellnessTracker.repositories.TeaRepository;

@Component
@Transactional
public class TeaService extends Service<Tea, Long> {

    @Autowired
    private TeaRepository teaRepository;

    @Override
    protected JpaRepository getRepository () {
        return teaRepository;
    }

    public Tea findByName ( final String name ) {
        return teaRepository.findByName( name );
    }
}
