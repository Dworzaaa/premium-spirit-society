package com.premium.spirit.society.core.dataLayer.DAO;

import com.premium.spirit.society.core.businessLayer.BO.display.UserDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;

import java.util.List;

/**
 * UserDAO interface
 */
public interface UserDAO extends GenericDAO<UserEntity> {

    /**
     * Find user by username.
     *
     * @param username the username
     * @return the user entity
     */
    public UserEntity findUserByUsername(String username);

    public UserEntity findUserByEmail(String email);

    public int saveUser(UserEntity userEntity);

    public List<UserEntity> findAllNonHidden();

    public void hide(int userId);

    public int getCountOfUnhiddenCustomers(String searchString);

    public List<UserEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString);
}
