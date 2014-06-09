package org.happyfaces.services;

import org.happyfaces.domain.User;
import org.happyfaces.services.base.IService;

/**
 * User service interface.
 * 
 * @author Ignas
 * 
 */
public interface IUserService extends IService<User> {

    /**
     * Loads user by user name.
     */
    User findByUserName(String username);

    /**
     * Encodes provided password and checks if its same as one user has.
     */
    boolean isPaswordCorrect(User user, String password);

    /**
     * Encodes new password, sets it to user and updates user.
     */
    void changePassword(User user, String newPassword);
}
