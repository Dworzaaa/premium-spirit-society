package com.premium.spirit.society.core.businessLayer.service;


import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;

import java.util.List;
import java.util.Locale;

/**
 * The Interface UserService extends GenericService
 */
public interface UserService extends GenericService<UserFormBO, UserEntity> {

    /**
     * Find user by username.
     *
     * @param username the username
     * @return the user entity
     */
    //TODO should return some VO
    public UserFormBO getUserByUsername(String username);

    //TODO should return some VO
    public UserFormBO getUserByEmail(String email);

    /**
     * creates new user with his locale
     *
     * @param user   as UserEntity
     * @param locale as Locale
     */
    public int create(UserFormBO user, Locale locale);

    public boolean isUniqueUsername(UserFormBO user);

    public boolean isUniqueEmail(UserFormBO user);

    public boolean isUniqueUsername(int userId, String username);

    public boolean isUniqueEmail(int userId, String email);

    public List<UserDisplayBO> findAllNonHidden();

    public void changePassword(UserFormBO user);

    public void hide(int userId);

    public void revokeRoles(int userId);

    public void restoreUser(int userId);

    public int getLoggedUserId();

    public int getCountOfUnhiddenCustomers(String searchString);

    public List<UserDisplayBO> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);

}
