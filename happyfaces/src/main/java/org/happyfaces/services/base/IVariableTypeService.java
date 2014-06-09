package org.happyfaces.services.base;

import org.happyfaces.domain.base.BaseEntity;
import org.happyfaces.domain.base.IEntity;

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
