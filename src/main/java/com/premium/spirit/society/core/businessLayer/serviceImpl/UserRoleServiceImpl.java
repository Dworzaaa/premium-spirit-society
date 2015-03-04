package com.premium.spirit.society.core.businessLayer.serviceImpl;

import com.premium.spirit.society.core.businessLayer.service.UserRoleService;
import com.premium.spirit.society.core.dataLayer.DAO.UserRoleDAO;
import com.premium.spirit.society.core.dataLayer.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class UserRoleServiceImpl.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    /**
     * The user role dao.
     */
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    @Transactional
    public List<UserRoleEntity> findAllUserRolesByUserID(int userID) {
        return userRoleDAO.findAllUserRolesByUserID(userID);
    }
}
