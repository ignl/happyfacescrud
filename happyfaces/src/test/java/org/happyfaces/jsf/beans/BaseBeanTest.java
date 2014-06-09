package org.happyfaces.jsf.beans;

import java.util.List;

import org.happyfaces.beans.base.BaseBean;
import org.happyfaces.domain.TestEntity;
import org.happyfaces.domain.base.IEntity;
import org.happyfaces.jsf.datatable.PaginationConfiguration;
import org.happyfaces.services.base.IService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Base backing bean methods tests.
 * 
 * @author Ignas
 * 
 */
public class BaseBeanTest {

    @SuppressWarnings("rawtypes")
    @Test
    public void testInitEntity() {
        BaseBean backingBean = new BaseBean<TestEntity>(TestEntity.class) {
            private static final long serialVersionUID = 1L;
            @Override
            protected IService<TestEntity> getPersistenceService() {
                return new DummyPersistenceService();
            }
        };
        
        // test new instance of TestEntity is created when objectId is not set.
        IEntity initEntity = backingBean.initEntity();
        Assert.assertNotNull(initEntity);
        Assert.assertTrue(initEntity instanceof TestEntity);
        Assert.assertNull(initEntity.getId());
        backingBean.setObjectId(22L);
        
        // 
        initEntity = backingBean.initEntity();
        Assert.assertNotNull(initEntity);
        Assert.assertTrue(initEntity instanceof TestEntity);
        Assert.assertEquals(Long.valueOf(22L), initEntity.getId());
        
        // not exist
        backingBean.setObjectId(23L);
        initEntity = backingBean.initEntity();
        Assert.assertNull(initEntity);
    }
    
    private static class DummyPersistenceService implements IService<TestEntity> {

        @Override
        public void add(TestEntity entity) {
        }

        @Override
        public void update(TestEntity entity) {
        }

        @Override
        public void delete(TestEntity entity) {
        }

        @Override
        public void delete(Long id) {
        }

        @Override
        public void deleteMany(Iterable<Long> ids) {
        }

        @Override
        public TestEntity findById(Long id) {
            if (id == 22L) {
                TestEntity entity = new TestEntity();
                entity.setId(22L);
                return entity;
            }
            return null;
        }

        @Override
        public TestEntity findById(Long id, List<String> fetchFields) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public long count(PaginationConfiguration config) {
            return 0;
        }

        @Override
        public List<TestEntity> list() {
            return null;
        }

        @Override
        public List<TestEntity> list(PaginationConfiguration config) {
            return null;
        }
        
    }
}
