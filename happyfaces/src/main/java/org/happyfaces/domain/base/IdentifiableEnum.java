package org.happyfaces.domain.base;

/**
 * Interface for identifiableEnum. This is used when enum is mapped in model as
 * integer.
 * 
 * @author Ignas
 * 
 */
public interface IdentifiableEnum {

    /**
     * Enum id that is saved in database.
     */
    Integer getId();
    
    /**
     * Enum label.
     */
    String getLabel();

}
