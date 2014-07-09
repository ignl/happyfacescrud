#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories;

import ${package}.customrepository.GenericRepository;
import ${package}.domain.Operation;

/**
 * Spring Data repository interface for {@link Operation} entity. Implementation
 * created by spring.
 * 
 * @author Ignas
 * 
 */
public interface OperationRepository extends GenericRepository<Operation, Long> {

}
