package org.happyfaces.ws.rest;

import javax.persistence.EntityNotFoundException;

import org.happyfaces.domain.Customer;
import org.happyfaces.services.ICustomerService;
import org.happyfaces.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * REST Webservices controller for Customers. All supported WS methods are mapped to this controller.
 * 
 * @author Ignas
 * 
 */
@Controller
public class CustomerRestController extends BaseRestController {

    /** Injected service. */
    @Autowired
    private ICustomerService customerService;

    /**
     * Return customer information. Load by customer id that are passed in parameters.
     */
    @RequestMapping(value = "/customer/{customeriId}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<CustomerDTO> getByJediIdAndGirId(@PathVariable("customeriId") Long customeriId) {

        Customer customer = customerService.findById(customeriId);

        if (customer == null) {
            throw new EntityNotFoundException();
        }

        CustomerDTO customerDTO = ObjectUtils.copy(customer, CustomerDTO.class);

        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
    }

}
