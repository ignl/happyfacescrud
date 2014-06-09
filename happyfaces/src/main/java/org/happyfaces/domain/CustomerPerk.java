package org.happyfaces.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.happyfaces.domain.base.BaseEntity;

/**
 * CustomerPerk domain model.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "CUSTOMER_PERK")
public class CustomerPerk extends BaseEntity {

    /** */
    private static final long serialVersionUID = 1L;
    
    /** Perk name. */
    @Column(name = "NAME", nullable = false)
    private String name;
    
    /** Perk description. */
    @Column(name = "DESCRIPTION")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
