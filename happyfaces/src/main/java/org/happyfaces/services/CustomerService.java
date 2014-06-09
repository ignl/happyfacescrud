package org.happyfaces.services;

import java.util.List;

import org.happyfaces.domain.Customer;
import org.happyfaces.domain.CustomerPerk;
import org.happyfaces.repositories.CustomerRepository;
import org.happyfaces.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Customer.
 * 
 * @author Ignas
 *
 */
@Service("customerService")
public class CustomerService extends BaseService<Customer> implements ICustomerService {

    /** */
    private static final long serialVersionUID = 1L;
    
    /** Injected repository. */
    @Autowired
    private CustomerRepository repository;
    
    /**
     * @see org.happyfaces.services.base.BaseService#getRepository()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected JpaRepository<Customer, Long> getRepository() {
        return (JpaRepository) repository;
    }

    /**
     * @see org.happyfaces.services.ICustomerService#getAllCustomerPerks()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerPerk> getAllCustomerPerks() {
        return em.createQuery("from CustomerPerk").getResultList();
    }

}
