package com.shekhar.todo.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TodoListTest {

	private EntityManager em;

	@Before
	public void setup() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("todos");
		em = entityManagerFactory.createEntityManager();
	}

	@After
	public void cleanup() {
		em.close();
	}

	@Test
	public void shouldCreateAValidTodoList() {
		User user = UserBuilder.user().withEmail("test_user@testemail.com")
				.withPassword("password")
				.withAddedHobby("running")
				.withAddedHobby("swimming")
				.withAddedHobby("coding")
				.build();
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		System.out.println(user);
		assertNotNull(user.getId());
		
		TodoList todoList = new TodoList("a",new DateTime().minusDays(2).toDate());
		todoList.setCreatedBy(user);
		
		TodoItem todoItem =new TodoItem();
		todoItem.setDueOn(new DateTime().plusDays(1).toDate());
		todoItem.setTask("prepare JPA2");
		
		
	
		user.setTodoLists(Arrays.asList(todoList));
		
		em.getTransaction().begin();
		
		em.persist(todoList);
		em.persist(todoItem);
		HashSet<TodoItem> todoItems = new HashSet<TodoItem>();
		todoItems.add(todoItem);
		todoList.setTodoItems(todoItems);
		em.persist(user);
		
		em.getTransaction().commit();
		
		
		
		assertEquals(1, user.getTodoLists().size());
		
		for (TodoList todoListz : user.getTodoLists()) {
			System.out.println(todoListz);
		}
	}

}
