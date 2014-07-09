#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.base;

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
