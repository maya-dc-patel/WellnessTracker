package edu.ncsu.csc.WellnessTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.WellnessTracker.models.Inventory;

/**
 * InventoryRepository is used to provide CRUD operations for the Inventory
 * model. Spring will generate appropriate code with JPA.
 *
 * @author Kai Presler-Marshall
 *
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
