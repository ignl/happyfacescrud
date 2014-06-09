package org.happyfaces.repositories;

import org.happyfaces.customrepository.GenericRepository;
import org.happyfaces.domain.Customer;

/**
 * Spring Data repository interface for {@link Customer} entity. Implementation created by spring.
 * 
 * @author Ignas
 *
 */
public interface CustomerRepository extends GenericRepository<Customer, Long> {
}