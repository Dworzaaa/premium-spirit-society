package com.premium.spirit.society.core.dataLayer.DAOImpl;

import com.premium.spirit.society.core.dataLayer.DAO.UserDAO;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of UserDAO
 * Extending implementation of GenericDAO
 */
@Repository
public class UserDAOImpl
        extends GenericDAOImpl<UserEntity>
        implements UserDAO {

    /* (non-Javadoc)
     * @see cz.cvut.fit.genepi.DAO.UserDAO#getUserByUsername(java.lang.String)
     */
    @Override
    public UserEntity findUserByUsername(String username) {

        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from UserEntity u left join fetch u.roles where u.username = :user_name")
                .setParameter("user_name", username);

        return (UserEntity) query.uniqueResult();
    }

    @Override
    public UserEntity findUserByEmail(String email) {

        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from UserEntity where contact.email = :email")
                .setParameter("email", email);

        return (UserEntity) query.uniqueResult();
    }

    @Override
    public int saveUser(UserEntity userEntity) {

        sessionFactory.getCurrentSession().saveOrUpdate(userEntity);

        return userEntity.getId();
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAllNonHidden() {

        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from UserEntity where hidden = false ORDER BY username");

        return (List<UserEntity>) query.list();
    }

    @Override
    public void hide(int userId) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE UserEntity SET hidden = true WHERE id = :userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public int getCountOfUnhiddenCustomers(String searchString) {
        Long count;
        count = ((Long) sessionFactory
                .getCurrentSession()
                .createQuery("select count(id) " +
                        "from UserEntity" +
                        " where customer=1 AND hidden=0 AND (contact.firstName like '" + searchString + "%'" +
                        " OR contact.lastName like '" + searchString + "%'" +
                        " OR   contact.email like '" + searchString + "%')")
                .uniqueResult());

        return count.intValue();
    }

    @Override
    public List<UserEntity> getBySearchStringWithPagination(int maxResults, int pageNumber, String searchString) {
        Query query;

        query = sessionFactory
                .getCurrentSession()
                .createQuery("from UserEntity" +
                        " where customer=1 AND hidden=0 AND (contact.firstName like '" + searchString + "%'" +
                        " OR contact.lastName like '" + searchString + "%'" +
                        " OR   contact.email like '" + searchString + "%') ORDER BY contact.lastName, contact.firstName")
                .setFirstResult(maxResults * (pageNumber - 1))
                .setMaxResults(maxResults);

        return (List<UserEntity>) query.list();
    }
}
