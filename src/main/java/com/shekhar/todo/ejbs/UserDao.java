package com.shekhar.todo.ejbs;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.shekhar.todo.domain.User;

/**
 * Session Bean implementation class UserDao
 */
@Singleton
public class UserDao {

	@PersistenceContext
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
