#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories;

import ${package}.customrepository.GenericRepository;
import ${package}.domain.Customer;

/**
 * Spring Data repository interface for {@link Customer} entity. Implementation created by spring.
 * 
 * @author Ignas
 *
 */
public interface CustomerRepository extends GenericRepository<Customer, Long> {
}