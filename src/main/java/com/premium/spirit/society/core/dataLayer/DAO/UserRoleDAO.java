package com.premium.spirit.society.core.dataLayer.DAO;


import com.premium.spirit.society.core.dataLayer.entity.UserRoleEntity;

import java.util.List;

/**
 * UserRoleDAO interface
 */
public interface UserRoleDAO extends GenericDAO<UserRoleEntity> {

    /**
     * Find all user roles by user id.
     *
     * @param userID the user id
     * @return the list
     */
    List<UserRoleEntity> findAllUserRolesByUserID(int userID);

}
