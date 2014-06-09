package org.happyfaces.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.happyfaces.domain.Customer;
import org.happyfaces.jsf.datatable.PaginationConfiguration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration Tests for Incident service.
 * 
 * @author Ignas
 * 
 */
@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class CustomerServiceTest {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDatabaseTester databaseTester;

    @Before
    public void setUp() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/CustomerTest.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        databaseTester.onSetup();
    }

    @After
    public void cleanup() throws Exception {
        databaseTester.onTearDown();
    }

    @Test
    public void testFindById() {
        Customer customer = customerService.findById(1L);
        Assert.assertEquals("tele2@aaa.com", customer.getEmail());
    }

    @Test
    public void testListAll() {
        List<Customer> customers = customerService.list();
        Assert.assertEquals(3, customers.size());
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(Long.valueOf(i+1), customers.get(i).getId());
        }
    }

    @Test
    public void testCountAll() {
        Assert.assertEquals(3, customerService.count());
    }

    @Test
    public void testPagination() {
        PaginationConfiguration config = new PaginationConfiguration(0, 2, null, null, null, null);
        List<Customer> customers = customerService.list(config);
        Assert.assertEquals(2, customers.size());
        Assert.assertEquals(Long.valueOf(1), customers.get(0).getId());
        Assert.assertEquals(Long.valueOf(2), customers.get(1).getId());
        config = new PaginationConfiguration(2, 2, null, null, null, null);
        customers = customerService.list(config);
        Assert.assertEquals(1, customers.size());
        Assert.assertEquals(Long.valueOf(3), customers.get(0).getId());
    }

    @Test
    public void testFilterByMultipleFilters() {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("name", "tele2");
        filters.put("phone", "123456");
        filters.put("address", "aaa");
        PaginationConfiguration config = new PaginationConfiguration(0, 5, filters, null, null, null);
        List<Customer> customers = customerService.list(config);
        Assert.assertEquals(1, customers.size());
        Assert.assertEquals(Long.valueOf(1), customers.get(0).getId());
    }

    @Test
    public void testCrud() throws Exception {
        databaseTester.onTearDown(); // crear db before tests
        // CREATE
        Assert.assertEquals(0, customerService.count());
        Customer customer = new Customer();
        customer.setName("Name");
        customer.setEmail("a@a.com");
        customer.setAddress("address");
        customer.setPhone("111333444");
        
        customerService.add(customer);
        Assert.assertEquals(1, customerService.count());

        // READ
        Customer readcustomer = customerService.list().get(0);
        Assert.assertEquals(readcustomer.getName(), "Name");
        Assert.assertEquals(readcustomer.getEmail(), "a@a.com");
        Assert.assertEquals(readcustomer.getAddress(), "address");
        Assert.assertEquals(readcustomer.getPhone(), "111333444");

        // UPDATE
        readcustomer.setName("updatedName");

        customerService.update(readcustomer);

        Customer updatedCustomer = customerService.list().get(0);
        Assert.assertEquals(updatedCustomer.getName(), "updatedName");

        // DELETE
        customerService.delete(updatedCustomer.getId());
        Assert.assertEquals(0, customerService.count());
    }

}
