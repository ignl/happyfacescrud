package org.happyfaces.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.happyfaces.domain.base.BaseEntity;

/**
 * City domain model.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "CITY")
public class City  extends BaseEntity {
    
    /** */
    private static final long serialVersionUID = 1L;

    /** City name. */
    private String name;
    
    /** Country of the city. */
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
