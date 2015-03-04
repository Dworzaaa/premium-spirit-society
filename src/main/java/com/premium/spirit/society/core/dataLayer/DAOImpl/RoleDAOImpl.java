package com.premium.spirit.society.core.dataLayer.DAOImpl;


import com.premium.spirit.society.core.dataLayer.DAO.RoleDAO;
import com.premium.spirit.society.core.dataLayer.entity.RoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Implementation of RoleDAO
 * Extending implementation of GenericDAO
 */
@Repository
public class RoleDAOImpl
        extends GenericDAOImpl<RoleEntity>
        implements RoleDAO {

}
