package org.happyfaces.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.happyfaces.domain.TestEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Date operations.
 * 
 * @author Ignas
 * 
 */
public class ObjectUtilsTest {
    
    @Test
    public void testCopy() {
        TestClass test = new TestClass();
        test.setStringField("stringField");
        test.setIntegerField(1);
        test.setLongField(2L);
        test.setBigDecimalField(BigDecimal.TEN);
        test.setDoubleField(2.2);
        TestEntity testEntity = new TestEntity();
        testEntity.setId(2L);
        test.setEntityField(testEntity);
        
        TestClass copy = ObjectUtils.copy(test, TestClass.class);
        
        Assert.assertEquals(test.getStringField(), copy.getStringField());
        Assert.assertEquals(test.getIntegerField(), copy.getIntegerField());
        Assert.assertEquals(test.getLongField(), copy.getLongField());
        Assert.assertEquals(test.getBigDecimalField(), copy.getBigDecimalField());
        Assert.assertEquals(test.getEntityField().getId(), copy.getEntityField().getId());
        Assert.assertEquals(test.getDoubleField(), copy.getDoubleField());
    }
    
    @Test
    public void testCopyDifferentClassesFromLessToMore() {
        TestClass2 test = new TestClass2();
        test.setStringField("stringField");
        test.setIntegerField(1);
        
        TestClass copy = new TestClass();
        
        ObjectUtils.copy(test, copy);
        
        Assert.assertEquals(test.getStringField(), copy.getStringField());
        Assert.assertEquals(test.getIntegerField(), copy.getIntegerField());
        Assert.assertEquals(null, copy.getLongField());
        Assert.assertEquals(null, copy.getBigDecimalField());
        Assert.assertEquals(null, copy.getEntityField());
        Assert.assertEquals(null, copy.getDoubleField());
    }
    
    @Test
    public void testCopyDifferentClassesFromMoreToLess() {
        TestClass test = new TestClass();
        test.setStringField("stringField");
        test.setIntegerField(1);
        test.setLongField(2L);
        test.setBigDecimalField(BigDecimal.TEN);
        test.setDoubleField(2.2);
        TestEntity testEntity = new TestEntity();
        testEntity.setId(2L);
        test.setEntityField(testEntity);
        
        TestClass2 copy = new TestClass2();
        
        ObjectUtils.copy(test, copy, true);
        
        Assert.assertEquals(test.getStringField(), copy.getStringField());
        Assert.assertEquals(test.getIntegerField(), copy.getIntegerField());
    }
    
    @Test
    public void testCopyToList() {
        TestClass test1 = new TestClass();
        test1.setStringField("stringField");
        test1.setIntegerField(1);
        test1.setLongField(2L);
        test1.setBigDecimalField(BigDecimal.TEN);
        test1.setDoubleField(2.2);
        TestEntity testEntity = new TestEntity();
        testEntity.setId(2L);
        test1.setEntityField(testEntity);
        
        TestClass test2 = new TestClass();
        test2.setStringField("stringField");
        test2.setIntegerField(1);
        test2.setLongField(2L);
        test2.setBigDecimalField(BigDecimal.TEN);
        test2.setDoubleField(2.2);
        TestEntity testEntity2 = new TestEntity();
        testEntity2.setId(1L);
        test2.setEntityField(testEntity2);
        
        List<TestClass> list = new ArrayList<ObjectUtilsTest.TestClass>();
        list.add(test1);
        list.add(test2);
        
        List<TestClass> copyList = ObjectUtils.copyToList(list, TestClass.class);
        
        Assert.assertEquals(list.size(), copyList.size());
       
        Assert.assertEquals(list.get(0).getStringField(), copyList.get(0).getStringField());
        Assert.assertEquals(list.get(0).getIntegerField(), copyList.get(0).getIntegerField());
        Assert.assertEquals(list.get(0).getLongField(), copyList.get(0).getLongField());
        Assert.assertEquals(list.get(0).getBigDecimalField(), copyList.get(0).getBigDecimalField());
        Assert.assertEquals(list.get(0).getEntityField().getId(), copyList.get(0).getEntityField().getId());
        Assert.assertEquals(list.get(0).getDoubleField(), copyList.get(0).getDoubleField());

        Assert.assertEquals(list.get(1).getStringField(), copyList.get(1).getStringField());
        Assert.assertEquals(list.get(1).getIntegerField(), copyList.get(1).getIntegerField());
        Assert.assertEquals(list.get(1).getLongField(), copyList.get(1).getLongField());
        Assert.assertEquals(list.get(1).getBigDecimalField(), copyList.get(1).getBigDecimalField());
        Assert.assertEquals(list.get(1).getEntityField().getId(), copyList.get(1).getEntityField().getId());
        Assert.assertEquals(list.get(1).getDoubleField(), copyList.get(1).getDoubleField());
    }
    
    public static class TestClass {
        private String stringField;
        private Integer integerField;
        private Long longField;
        private BigDecimal bigDecimalField;
        private TestEntity entityField;
        private Double doubleField;
        public String getStringField() {
            return stringField;
        }
        public void setStringField(String stringField) {
            this.stringField = stringField;
        }
        public Integer getIntegerField() {
            return integerField;
        }
        public void setIntegerField(Integer integerField) {
            this.integerField = integerField;
        }
        public Long getLongField() {
            return longField;
        }
        public void setLongField(Long longField) {
            this.longField = longField;
        }
        public BigDecimal getBigDecimalField() {
            return bigDecimalField;
        }
        public void setBigDecimalField(BigDecimal bigDecimalField) {
            this.bigDecimalField = bigDecimalField;
        }
        public TestEntity getEntityField() {
            return entityField;
        }
        public void setEntityField(TestEntity entityField) {
            this.entityField = entityField;
        }
        public Double getDoubleField() {
            return doubleField;
        }
        public void setDoubleField(Double doubleField) {
            this.doubleField = doubleField;
        }
    }
    
    public static class TestClass2 {
        private String stringField;
        private Integer integerField;
        public String getStringField() {
            return stringField;
        }
        public void setStringField(String stringField) {
            this.stringField = stringField;
        }
        public Integer getIntegerField() {
            return integerField;
        }
        public void setIntegerField(Integer integerField) {
            this.integerField = integerField;
        }
    }
}
