#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ${package}.beans.base.BaseBean;
import ${package}.domain.Operation;
import ${package}.services.IOperationService;
import ${package}.services.base.IService;

/**
 * Backing bean for operations UI.
 * 
 * @author Ignas
 *
 */
@ManagedBean(name = "operationBean")
@ViewScoped
public class OperationBean extends BaseBean<Operation> {

    /** */
    private static final long serialVersionUID = 1L;
    
    /** Injected service. */
    @ManagedProperty(value = "${symbol_pound}{operationService}")
    private IOperationService operationService;

    /**
     * Constructor.
     */
    public OperationBean() {
        super(Operation.class);
    }

    /**
     * @see ${package}.beans.base.BaseBean${symbol_pound}getPersistenceService()
     */
    @Override
    protected IService<Operation> getPersistenceService() {
        return operationService;
    }

    public void setOperationService(IOperationService operationService) {
        this.operationService = operationService;
    }
    
    /**
     * Fetch account field so no LazyInitialize exception is thrown when
     * we access it from account list view.
     * 
     * @see ${package}.beans.base.BaseBean${symbol_pound}getListFieldsToFetch()
     */
    @Override
    protected List<String> getListFieldsToFetch() {
        return Arrays.asList("account");
    }
    
    /**
     * Fetch customer field so no LazyInitialize exception is thrown when
     * we access it from account edit view.
     * 
     * @see ${package}.beans.base.BaseBean${symbol_pound}getFormFieldsToFetch()
     */
    @Override
    protected List<String> getFormFieldsToFetch() {
        return Arrays.asList("account");
    }
}
