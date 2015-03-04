package com.premium.spirit.society.core.businessLayer.service;

import com.premium.spirit.society.core.dataLayer.entity.UserRoleEntity;

import java.util.List;

/**
 * The Interface UserRoleService extends GenericService
 */
public interface UserRoleService {

    /**
     * Find all user roles by user id.
     *
     * @param userID the user id
     * @return the list
     */
    public List<UserRoleEntity> findAllUserRolesByUserID(int userID);

}
