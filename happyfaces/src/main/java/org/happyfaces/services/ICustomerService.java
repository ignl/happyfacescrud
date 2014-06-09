package org.happyfaces.services;

import java.util.List;

import org.happyfaces.domain.Customer;
import org.happyfaces.domain.CustomerPerk;
import org.happyfaces.services.base.IService;

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
