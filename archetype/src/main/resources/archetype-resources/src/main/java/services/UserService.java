#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ${package}.domain.User;
import ${package}.repositories.UserRepository;
import ${package}.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for User. Besides providing all default methods it is also used in Spring
 * Security login (implements {@link UserDetailsService}).
 * 
 * @author Ignas
 * 
 */
@Service("userService")
public class UserService extends BaseService<User> implements IUserService, UserDetailsService {

    /** */
    private static final long serialVersionUID = 1L;
    
    /**
     * Spring Data repository for {@link User} entity.
     */
    @Autowired
    private UserRepository repository;

    /**
     * @see ${package}.services.base.BaseService${symbol_pound}getRepository()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected JpaRepository<User, Long> getRepository() {
        return (JpaRepository) repository;
    }

    /**
     * @see ${package}.services.IUserService${symbol_pound}findByUserName(java.lang.String)
     */
    @Override
    public User findByUserName(String username) {
        return repository.findByUsername(username);
    }

    /**
     * Used by Spring security for login.
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService${symbol_pound}loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String userName) {
        final User user = findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by username " + userName);
        }
        return new HappyfacesUserDetails(user);
    }
    
    /**
     * @see ${package}.services.IUserService${symbol_pound}isPaswordCorrect(${package}.domain.User, java.lang.String)
     */
    @Override
    public boolean isPaswordCorrect(User user, String password) {
        // load fresh user version from db to be sure to have current password.
        User userFromDb = findById(user.getId());
        PasswordEncoder encoder = new StandardPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        return encodedPassword.equals(userFromDb.getPassword());
    }
    
    /**
     * @see ${package}.services.IUserService${symbol_pound}changePassword(${package}.domain.User, java.lang.String)
     */
    @Override
    @Transactional(readOnly = false)
    public void changePassword(User user, String newPassword) {
        PasswordEncoder encoder = new StandardPasswordEncoder();
        String encodedPassword = encoder.encode(newPassword);
        // load fresh user version from db to have managed version and avoid optimistic lock exception.
        User userFromDb = findById(user.getId());
        userFromDb.setPassword(encodedPassword);
        update(userFromDb);
    }

    /**
     * Custom {@link UserDetails} implementation that additionally contains
     * logged in {@link User} object. That way all user information is
     * accessible through Spring security. Its simply enough to convert
     * UserDetails to JediUserDetails and get User from there.
     * 
     * @author Ignas
     * 
     */
    public static final class HappyfacesUserDetails implements UserDetails {

        /**
         * Class version id for serialization. After a change to serialized field this number should be changed so it would
         * be clear its different class version.
         */
        private static final long serialVersionUID = 1L;

        /** User. */
        private User user;

        /** Constructor. */
        private HappyfacesUserDetails(User user) {
            super();
            this.user = user;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }
        
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new GrantedAuthority() {
                private static final long serialVersionUID = 1L;

                @Override
                public String getAuthority() {
                    return "ROLE_USER";
                }
            });
            return roles;
        }

        public User getUser() {
            return user;
        }

    }

}
