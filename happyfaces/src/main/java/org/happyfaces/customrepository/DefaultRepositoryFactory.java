package org.happyfaces.customrepository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

/**
 * Overridden factory to return custom {@link GenericRepositoryImpl} repository
 * implementation instead of standard spring ones.
 * 
 * @author Ignas
 * 
 */
public class DefaultRepositoryFactory extends JpaRepositoryFactory {

	/**
	 * Constructor.
	 * 
	 * @param entityManager
	 *            JPA entity manager.
	 */
	public DefaultRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		Assert.notNull(entityManager);
	}

	/**
	 * @see org.springframework.data.jpa.repository.support.JpaRepositoryFactory#getTargetRepository(org.springframework.data.repository.core.RepositoryMetadata,
	 *      javax.persistence.EntityManager)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected final <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
			RepositoryMetadata metadata, EntityManager entityManager) {

		Class<?> repositoryInterface = metadata.getRepositoryInterface();
		JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata
				.getDomainType());
		return new GenericRepositoryImpl(entityInformation, entityManager,
				repositoryInterface);
	}

	/**
	 * @see org.springframework.data.jpa.repository.support.JpaRepositoryFactory#getRepositoryBaseClass(org.springframework.data.repository.core.RepositoryMetadata)
	 */
	@Override
	protected final Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return GenericRepositoryImpl.class;
	}

}
