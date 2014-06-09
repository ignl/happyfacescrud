package org.happyfaces.customrepository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * This class is use when you use DefaultRepositoryFactory to override default
 * repository factory class.
 * 
 * @author Ignas
 *
 * @param <T> Repository type.
 * @param <S> Entity type.
 * @param <ID> Entity ID type.
 */
public class DefaultRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends
        JpaRepositoryFactoryBean<T, S, ID> {

    /**
     * Returns a {@link RepositoryFactorySupport}.
     */
    protected final RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new DefaultRepositoryFactory(entityManager);
    }

}