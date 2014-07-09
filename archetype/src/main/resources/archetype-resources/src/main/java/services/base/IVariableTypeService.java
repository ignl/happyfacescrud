#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.base;

import ${package}.domain.base.BaseEntity;
import ${package}.domain.base.IEntity;

/**
 * Service interface for service with no concrete Entity behind.
 * 
 * @author Ignas
 * 
 */
public interface IVariableTypeService {
    
    /**
     * Loads entity by its id.
     * 
     * @param entityClass
     *            Concrete Entity class.
     * @param id
     *            Entity ID
     * @return Loaded Entity.
     */
    BaseEntity findById(Class<? extends IEntity> entityClass, Long id);

}
