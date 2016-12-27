package com.app.springmvc.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.app.springmvc.model.User;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

@Repository("userDao")
public class UserDAO extends AbstractDAO<User, Integer> {

    
    public UserDAO() {
        super(User.class);
    }

    public User findById(int id) {
        User user = (User) getOne(id);
        if (user != null) {
            initializeCollection(user.getUserProfiles());
        }
        return user;
    }

    public User findBySSO(String sso) {
        System.out.println("SSO : " + sso);
        try {
            User user = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.ssoId LIKE :ssoId")
                    .setParameter("ssoId", sso)
                    .getSingleResult();

            if (user != null) {
                initializeCollection(user.getUserProfiles());
            }
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        List<User> users = getEntityManager()
                .createQuery("SELECT u FROM User u ORDER BY u.firstName ASC")
                .getResultList();
        return users;
    }

    public void save(User user) {
        super.save(user);
    }

    public void deleteBySSO(String sso) {
        User user = (User) getEntityManager()
                .createQuery("SELECT u FROM User u WHERE u.ssoId LIKE :ssoId")
                .setParameter("ssoId", sso)
                .getSingleResult();
        delete(user);
    }

    //An alternative to Hibernate.initialize()
    protected void initializeCollection(Collection<?> collection) {
        if (collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }



}
