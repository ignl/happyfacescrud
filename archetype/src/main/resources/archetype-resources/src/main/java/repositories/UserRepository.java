#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories;

import ${package}.customrepository.GenericRepository;
import ${package}.domain.User;

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
