package com.shekhar.todo.cdi.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.shekhar.todo.domain.User;

public class UserRepository {

	@Inject
	private EntityManager em;
	
	public List<User> findAllUsersOrderedByRegisterationDate() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> User = criteria.from(User.class);
        criteria.select(User).orderBy(cb.asc(User.get("registeredOn")));
        return em.createQuery(criteria).getResultList();
	}

	public User findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> user = criteria.from(User.class);
		criteria.select(user).where(cb.equal(user.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}

}
