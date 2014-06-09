package org.happyfaces.services;

import org.happyfaces.domain.City;
import org.happyfaces.repositories.CityRepository;
import org.happyfaces.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Service implementation for City.
 * 
 * @author Ignas
 *
 */
@Service("cityService")
public class CityService extends BaseService<City> implements ICityService {

    /** */
    private static final long serialVersionUID = 1L;

    /** Injected repository. */
    @Autowired
    private CityRepository repository;
    
    /**
     * @see org.happyfaces.services.base.BaseService#getRepository()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected JpaRepository<City, Long> getRepository() {
        return (JpaRepository) repository;
    }
}
