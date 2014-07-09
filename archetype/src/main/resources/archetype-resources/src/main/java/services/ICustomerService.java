#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import java.util.List;

import ${package}.domain.Customer;
import ${package}.domain.CustomerPerk;
import ${package}.services.base.IService;

/**
 * Customer service interface.
 * 
 * @author Ignas
 * 
 */
public interface ICustomerService extends IService<Customer> {

    /**
     * Loads all customer perks.
     */
    List<CustomerPerk> getAllCustomerPerks();

}
