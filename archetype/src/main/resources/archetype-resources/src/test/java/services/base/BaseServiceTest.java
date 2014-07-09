#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ${package}.domain.base.IEntity;
import ${package}.jsf.datatable.PaginationConfiguration;
import ${package}.services.base.BaseService;
import ${package}.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.primefaces.model.SortOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mysema.query.types.Predicate;

/**
 * Unit tests for base service.
 * 
 * @author Ignas
 * 
 */
public class BaseServiceTest {

    @SuppressWarnings({ "serial", "rawtypes" })
    @Test
    public void testGetPredicate() {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("testText", "text");
        filters.put("testEntity", new TestEntity());
        filters.put("testBigDecimal", new BigDecimal("1.1"));
        filters.put("testByte", new Byte("1"));
        filters.put("testShort", new Short("2"));
        filters.put("testInteger", new Integer("3"));
        filters.put("testLong", new Long("4"));
        filters.put("testDouble", new Double("5.5"));
        filters.put("testFloat", new Float("6.6"));
        filters.put("testDate", DateUtils.createDate(2013, 1, 1));
        PaginationConfiguration configuration = new PaginationConfiguration(0, 10, filters, Arrays.asList("entity1"),
                "test1", SortOrder.ASCENDING);
        BaseService<TestEntity> service = new BaseService<TestEntity>() {
            @SuppressWarnings("unchecked")
            @Override
            protected JpaRepository getRepository() {
                return null;
            }
        };

        Predicate predicate = service.getPredicate(configuration);
        String predicateString = predicate.toString();
        Assert.assertTrue(predicateString.contains("testEntity.testShort = 2"));
        Assert.assertTrue(predicateString.contains("testEntity.testFloat = 6.6"));
        Assert.assertTrue(predicateString.contains("testEntity.testLong = 4"));
        Assert.assertTrue(predicateString.contains("testEntity.testBigDecimal = 1.1"));
        Assert.assertTrue(predicateString.contains("testEntity.testInteger = 3"));
        Assert.assertTrue(predicateString.contains("startsWithIgnoreCase(testEntity.testText,text)"));
        Assert.assertTrue(predicateString.contains("testEntity.testByte = 1"));
        Assert.assertTrue(predicateString.contains("testEntity.testDouble = 5.5"));
        Assert.assertTrue(predicateString.contains("testEntity.testDate = Tue Jan 01 00:00:00"));
    }

    @SuppressWarnings({ "serial", "rawtypes" })
    @Test
    public void testGetPredicateForRangeSearch() {
        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("fromRange-testBigDecimal", new BigDecimal("1.1"));
        filters.put("toRange-testBigDecimal", new BigDecimal("1.2"));
        filters.put("fromRange-testByte", new Byte("1"));
        filters.put("toRange-testByte", new Byte("2"));
        filters.put("fromRange-testShort", new Short("2"));
        filters.put("toRange-testShort", new Short("3"));
        filters.put("fromRange-testInteger", new Integer("3"));
        filters.put("toRange-testInteger", new Integer("4"));
        filters.put("fromRange-testLong", new Long("4"));
        filters.put("toRange-testLong", new Long("5"));
        filters.put("fromRange-testDouble", new Double("5.5"));
        filters.put("toRange-testDouble", new Double("6.5"));
        filters.put("fromRange-testFloat", new Float("6.6"));
        filters.put("toRange-testFloat", new Float("7.6"));
        filters.put("fromRange-testDate", DateUtils.createDate(2013, 2, 1));
        filters.put("toRange-testDate", DateUtils.createDate(2013, 3, 1));
        PaginationConfiguration configuration = new PaginationConfiguration(0, 10, filters, Arrays.asList("entity1"),
                "test1", SortOrder.ASCENDING);
        BaseService<TestEntity> service = new BaseService<TestEntity>() {
            @SuppressWarnings("unchecked")
            @Override
            protected JpaRepository getRepository() {
                return null;
            }
        };

        Predicate predicate = service.getPredicate(configuration);
        String predicateString = predicate.toString();
        
        Assert.assertTrue(predicateString.contains("testEntity.testDouble >= 5.5"));
        Assert.assertTrue(predicateString.contains("testEntity.testDouble <= 6.5"));
        Assert.assertTrue(predicateString.contains("testEntity.testShort >= 2"));
        Assert.assertTrue(predicateString.contains("testEntity.testShort <= 3"));
        Assert.assertTrue(predicateString.contains("testEntity.testFloat <= 7.6"));
        Assert.assertTrue(predicateString.contains("testEntity.testFloat >= 6.6"));
        Assert.assertTrue(predicateString.contains("testEntity.testInteger >= 3"));
        Assert.assertTrue(predicateString.contains("testEntity.testInteger <= 4"));
        Assert.assertTrue(predicateString.contains("testEntity.testDate <= Fri Mar 01 00:00:00"));
        Assert.assertTrue(predicateString.contains("testEntity.testDate >= Fri Feb 01 00:00:00"));
        Assert.assertTrue(predicateString.contains("testEntity.testLong >= 4"));
        Assert.assertTrue(predicateString.contains("testEntity.testLong <= 5"));
        Assert.assertTrue(predicateString.contains("testEntity.testByte >= 1"));
        Assert.assertTrue(predicateString.contains("testEntity.testByte <= 2"));
        Assert.assertTrue(predicateString.contains("testEntity.testBigDecimal <= 1.2"));
        Assert.assertTrue(predicateString.contains("testEntity.testBigDecimal >= 1.1"));
    }

    private static class TestEntity implements IEntity {
        @Override
        public Serializable getId() {
            return null;
        }

        @Override
        public boolean isTransient() {
            return false;
        }
    }
}
