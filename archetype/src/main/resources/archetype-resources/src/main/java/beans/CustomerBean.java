#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ${package}.beans.base.BaseBean;
import ${package}.domain.Customer;
import ${package}.domain.CustomerPerk;
import ${package}.services.ICustomerService;
import ${package}.services.base.IService;
import org.primefaces.model.DualListModel;

/**
 * Backing bean for customer UI.
 * 
 * @author Ignas
 *
 */
@ManagedBean(name = "customerBean")
@ViewScoped
public class CustomerBean extends BaseBean<Customer> {

    /** */
    private static final long serialVersionUID = 1L;
    
    /** Injected service. */
    @ManagedProperty(value = "${symbol_pound}{customerService}")
    private ICustomerService customerService;

    /** Customer perks list model for primefaces pick list component. */
    private DualListModel<CustomerPerk> perks;
    
    /**
     * Constructor.
     */
    public CustomerBean() {
        super(Customer.class);
    }

    @Override
    public IService<Customer> getPersistenceService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
    
    public List<Customer> getCustomers() {
        return customerService.list();
    }
    
    /**
     * Standard method for custom component with listType="pickList".
     */
    public DualListModel<CustomerPerk> getDualListModel() {
        if (perks == null) {
            List<CustomerPerk> perksSource = customerService.getAllCustomerPerks();
            List<CustomerPerk> perksTarget = new ArrayList<CustomerPerk>();
            if (getEntity().getPerks() != null) {
                perksTarget.addAll(getEntity().getPerks());
            }
            perksSource.removeAll(perksTarget);
            perks = new DualListModel<CustomerPerk>(perksSource, perksTarget);
        }
        return perks;
    }
    
    /**
     * Sets dual list model.
     */
    public void setDualListModel(DualListModel<CustomerPerk> perks) {
        getEntity().setPerks(perks.getTarget());
    }
    
    /**
     * Fetch city field so when not LazyInitialize exception is thrown when
     * we access it from account list view.
     * 
     * @see ${package}.beans.base.BaseBean${symbol_pound}getListFieldsToFetch()
     */
    @Override
    protected List<String> getListFieldsToFetch() {
        return Arrays.asList("city", "perks");
    }

    /**
     * Fetch city field so when not LazyInitialize exception is thrown when
     * we access it from account edit view.
     * 
     * @see ${package}.beans.base.BaseBean${symbol_pound}getFormFieldsToFetch()
     */
    @Override
    protected List<String> getFormFieldsToFetch() {
        return Arrays.asList("city", "perks");
    }

}
