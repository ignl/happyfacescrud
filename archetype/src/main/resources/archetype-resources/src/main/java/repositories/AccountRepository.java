#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories;

import ${package}.customrepository.GenericRepository;
import ${package}.domain.Account;

/**
 * Spring Data repository interface for {@link Account} entity. Implementation
 * created by spring.
 * 
 * @author Ignas
 * 
 */
public interface AccountRepository extends GenericRepository<Account, Long> {

}
