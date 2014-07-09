#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

/**
 * Enum for all operation types.
 * 
 * @author Ignas
 * 
 */
public enum OperationType {
    
    /** Credit operation. */
    CREDIT(1, "operation.credit"), 
    /** Debit operation. */
    DEBIT(2, "operation.debit");
    
    /** Enum id. */
    private Integer id;
    
    /** Enum label. */
    private String label;
    
    /**
     * Constructor.
     */
    private OperationType(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
    
    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

}
