package com.shekhar.todo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.shekhar.todo.domain.User;

/**
 * Example of EJB Stateless session bean
 * 
 * @author shekhargulati
 * 
 */
@Stateless
public class UserRegisterationService {

	@Inject
	private EntityManager entityManager;

	@Inject
	Logger logger;

	@Inject
	private Event<User> userEventSrc;

	public User register(User user) {
		logger.info("Registering User : " + user.getEmail());
		entityManager.persist(user);
		userEventSrc.fire(user);
		return user;
	}

	public User search(Long id) {
		logger.info("Finding user with id : " + id);
		return entityManager.find(User.class, id);
	}

	public void unregister(User user) {
		logger.info("Unregistering user with with id " + user.getId());

		entityManager.remove(user);
	}

	public User update(User user) {
		logger.info("Updating user with id " + user.getId());
		User mergedUser = entityManager.merge(user);
		return mergedUser;
	}
}
