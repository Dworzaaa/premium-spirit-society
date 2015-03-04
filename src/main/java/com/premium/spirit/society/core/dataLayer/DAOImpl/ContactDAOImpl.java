package com.premium.spirit.society.core.dataLayer.DAOImpl;

import com.premium.spirit.society.core.dataLayer.DAO.ContactDAO;
import com.premium.spirit.society.core.dataLayer.entity.ContactEntity;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ContactDAO
 * Extending implementation of GenericDAO
 */
@Repository
public class ContactDAOImpl
        extends GenericDAOImpl<ContactEntity>
        implements ContactDAO {

}
