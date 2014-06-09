package org.happyfaces.repositories;

import org.happyfaces.customrepository.GenericRepository;
import org.happyfaces.domain.Account;

/**
 * Spring Data repository interface for {@link Account} entity. Implementation
 * created by spring.
 * 
 * @author Ignas
 * 
 */
public interface AccountRepository extends GenericRepository<Account, Long> {

}
