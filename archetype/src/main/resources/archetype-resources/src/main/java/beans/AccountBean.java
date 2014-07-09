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
import ${package}.domain.Account;
import ${package}.services.IAccountService;
import ${package}.services.base.IService;

/**
 * Backing bean for account UI.
 * 
 * @author Ignas
 *
 */
@ManagedBean(name = "accountBean")
@ViewScoped
public class AccountBean extends BaseBean<Account> {

    /** */
    private static final long serialVersionUID = 1L;

    /** Injected service. */
    @ManagedProperty(value = "${symbol_pound}{accountService}")
    private IAccountService accountService;

    /**
     * Constructor.
     */
    public AccountBean() {
        super(Account.class);
    }

    /**
     * @see ${package}.beans.base.BaseBean${symbol_pound}getPersistenceService()
     */
    @Override
    protected IService<Account> getPersistenceService() {
        return accountService;
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Fetch customer field so no LazyInitialize exception is thrown when
     * we access it from account list view.
     * 
     * @see ${package}.beans.base.BaseBean${symbol_pound}getListFieldsToFetch()
     */
    @Override
    protected List<String> getListFieldsToFetch() {
        return Arrays.asList("customer", "operations");
    }

    /**
     * Fetch customer field so no LazyInitialize exception is thrown when
     * we access it from account edit view.
     * 
     * @see ${package}.beans.base.BaseBean${symbol_pound}getFormFieldsToFetch()
     */
    @Override
    protected List<String> getFormFieldsToFetch() {
        return Arrays.asList("customer", "operations");
    }

}
