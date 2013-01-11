package com.shekhar.todo.cdi.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.shekhar.todo.domain.TodoItem;

public class TodoItemRepository {
	
	@Inject
	private EntityManager em;
	
	public List<TodoItem> findAllTodoItemsOrderedByCreationDate() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TodoItem> criteria = cb.createQuery(TodoItem.class);
        Root<TodoItem> todoItem = criteria.from(TodoItem.class);
        criteria.select(todoItem).orderBy(cb.asc(todoItem.get("createdOn")));
        return em.createQuery(criteria).getResultList();
	}


}
