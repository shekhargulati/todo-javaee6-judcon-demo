package com.shekhar.todo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.shekhar.todo.domain.TodoList;

@Stateless
public class TodoListService {

	@Inject
	private EntityManager entityManager;

	@Inject
	Logger logger;
	
	@Inject
	Event<TodoList> todoEvent;

	public TodoList create(TodoList todoList) {
		logger.info("Persisting TodoList with name " + todoList.getName());
		entityManager.persist(todoList);
		todoEvent.fire(todoList);
		return todoList;
	}
}
