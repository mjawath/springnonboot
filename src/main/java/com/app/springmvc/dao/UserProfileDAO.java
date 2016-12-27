package com.app.springmvc.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.app.springmvc.model.UserProfile;

@Repository("userProfileDao")
public class UserProfileDAO extends AbstractDAO<UserProfile, Integer> {

    public UserProfileDAO() {
        super(UserProfile.class);
    }

//	public UserProfile findById(int id) {
//		return getByKey(id);
//	}
    public UserProfile findByType(String type) {
        System.out.println("type: " + type);
        try {
            UserProfile userProfile = (UserProfile) getEntityManager()
                    .createQuery("SELECT p FROM UserProfile p WHERE p.type LIKE :type")
                    .setParameter("type", type)
                    .getSingleResult();
            return userProfile;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll() {
        List<UserProfile> userProfiles = getEntityManager()
                .createQuery("SELECT p FROM UserProfile p  ORDER BY p.type ASC")
                .getResultList();
        return userProfiles;
    }

}
