package com.premium.spirit.society.core.dataLayer.DAOImpl;

import com.premium.spirit.society.core.dataLayer.DAO.UserRoleDAO;
import com.premium.spirit.society.core.dataLayer.entity.UserRoleEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of UserRoleDAO
 * Extending implementation of GenericDAO
 */
@Repository
public class UserRoleDAOImpl
        extends GenericDAOImpl<UserRoleEntity>
        implements UserRoleDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRoleEntity> findAllUserRolesByUserID(int user_id) {
        List<UserRoleEntity> userRoleEntities;
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from UserRoleEntity where userId = :userID");
        query.setParameter("userID", user_id);
        userRoleEntities = (List<UserRoleEntity>) query.list();
        return userRoleEntities;
    }

}
