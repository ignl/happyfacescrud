package org.happyfaces.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.happyfaces.domain.base.BaseEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * User domain model.
 * 
 * @author Ignas
 *
 */
@Entity
@Table(name = "APP_USER")
public class User extends BaseEntity {
    
    /** */
    private static final long serialVersionUID = 1L;
    
    /** User first name. */
    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    /** User last name. */
    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    /** Username. */
    @NotEmpty
    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String username;
    
    /** User password. Encoded. */
    @Column(name = "PASSWORD")
    private String password;

    /** User email. */
    @NotEmpty
    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
