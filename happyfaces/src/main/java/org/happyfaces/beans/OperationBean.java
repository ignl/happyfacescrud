package org.happyfaces.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.happyfaces.beans.base.BaseBean;
import org.happyfaces.domain.Operation;
import org.happyfaces.services.IOperationService;
import org.happyfaces.services.base.IService;

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
    @ManagedProperty(value = "#{operationService}")
    private IOperationService operationService;

    /**
     * Constructor.
     */
    public OperationBean() {
        super(Operation.class);
    }

    /**
     * @see org.happyfaces.beans.base.BaseBean#getPersistenceService()
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
     * @see org.happyfaces.beans.base.BaseBean#getListFieldsToFetch()
     */
    @Override
    protected List<String> getListFieldsToFetch() {
        return Arrays.asList("account");
    }
    
    /**
     * Fetch customer field so no LazyInitialize exception is thrown when
     * we access it from account edit view.
     * 
     * @see org.happyfaces.beans.base.BaseBean#getFormFieldsToFetch()
     */
    @Override
    protected List<String> getFormFieldsToFetch() {
        return Arrays.asList("account");
    }
}
