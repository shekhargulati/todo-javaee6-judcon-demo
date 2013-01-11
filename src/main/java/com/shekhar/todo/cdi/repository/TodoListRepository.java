package com.shekhar.todo.cdi.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.shekhar.todo.domain.TodoList;

public class TodoListRepository {
	
	@Inject
	private EntityManager em;
	
	public List<TodoList> findAllTodoListsOrderedByCreationDate() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TodoList> criteria = cb.createQuery(TodoList.class);
        Root<TodoList> todoList = criteria.from(TodoList.class);
        criteria.select(todoList).orderBy(cb.asc(todoList.get("createdOn")));
        return em.createQuery(criteria).getResultList();
	}


}
