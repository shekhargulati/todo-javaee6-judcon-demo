package com.shekhar.todo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.shekhar.todo.domain.TodoList;

@Stateless
public class TodoListService {

	@Inject
	private EntityManager entityManager;

	@Inject
	Logger logger;

	public TodoList create(TodoList todoList) {
		logger.info("Persisting TodoList with name " + todoList.getName());
		entityManager.persist(todoList);
		return todoList;
	}
}
