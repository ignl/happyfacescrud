#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import ${package}.domain.User;
import ${package}.jsf.datatable.PaginationConfiguration;
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
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDatabaseTester databaseTester;

    @Before
    public void setUp() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/UserTest.xml"));
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
        User user = userService.findById(100L);
        Assert.assertEquals("ignas@aaa.com", user.getEmail());
    }

    @Test
    public void testListAll() {
        List<User> users = userService.list();
        Assert.assertEquals(3, users.size());
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(Long.valueOf(100 + i), users.get(i).getId());
        }
    }

    @Test
    public void testCountAll() {
        Assert.assertEquals(3, userService.count());
    }

    @Test
    public void testPagination() {
        PaginationConfiguration config = new PaginationConfiguration(0, 2, null, null, null, null);
        List<User> users = userService.list(config);
        Assert.assertEquals(2, users.size());
        Assert.assertEquals(Long.valueOf(100), users.get(0).getId());
        Assert.assertEquals(Long.valueOf(101), users.get(1).getId());
        config = new PaginationConfiguration(2, 2, null, null, null, null);
        users = userService.list(config);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(Long.valueOf(102), users.get(0).getId());
    }

    @Test
    public void testFilterByFirstName() {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("firstName", "Ignas");
        PaginationConfiguration config = new PaginationConfiguration(0, 5, filters, null, null, null);
        List<User> users = userService.list(config);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(Long.valueOf(100), users.get(0).getId());
    }

    @Test
    public void testCrud() throws Exception {
        databaseTester.onTearDown(); // clear db before tests
        // CREATE
        Assert.assertEquals(0, userService.count());
        User user = new User();
        user.setFirstName("Name");
        user.setLastName("Name");
        user.setEmail("a@a.com");
        user.setUsername("username");
        
        userService.add(user);
        Assert.assertEquals(1, userService.count());

        // READ
        User readuser = userService.list().get(0);
        Assert.assertEquals(readuser.getFirstName(), "Name");
        Assert.assertEquals(readuser.getLastName(), "Name");
        Assert.assertEquals(readuser.getEmail(), "a@a.com");
        Assert.assertEquals(readuser.getUsername(), "username");

        // UPDATE
        readuser.setFirstName("updatedName");

        userService.update(readuser);

        User updateduser = userService.list().get(0);
        Assert.assertEquals(updateduser.getFirstName(), "updatedName");

        // DELETE
        userService.delete(updateduser.getId());
        Assert.assertEquals(0, userService.count());
    }

}
