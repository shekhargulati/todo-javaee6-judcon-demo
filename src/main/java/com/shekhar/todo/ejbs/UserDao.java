package com.shekhar.todo.ejbs;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.shekhar.todo.domain.User;

/**
 * Session Bean implementation class UserDao
 */
@Singleton
public class UserDao {

	@Inject
	private EntityManager entityManager;

    public User createUser(User user){
    	entityManager.persist(user);
    	return user;
    }
    
    public User findUser(Long id){
    	return entityManager.find(User.class, id);
    }
    
    public void delete(User user){
    	entityManager.remove(user);
    }
    
    public User update(User user){
    	User mergedUser = entityManager.merge(user);
    	return mergedUser;
    }
}
