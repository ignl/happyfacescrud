package org.happyfaces.repositories;

import org.happyfaces.customrepository.GenericRepository;
import org.happyfaces.domain.User;

/**
 * Spring Data repository interface for {@link User} entity. Implementation created by spring.
 * 
 * @author Ignas
 *
 */
public interface UserRepository extends GenericRepository<User, Long> {
    
    /**
     * Loads User by his unique username.
     */
    User findByUsername(String username);

}
