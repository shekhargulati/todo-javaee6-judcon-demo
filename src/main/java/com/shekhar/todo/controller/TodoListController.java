package com.shekhar.todo.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.shekhar.todo.cdi.repository.UserRepository;
import com.shekhar.todo.domain.TodoList;
import com.shekhar.todo.domain.User;
import com.shekhar.todo.service.TodoListService;
import com.shekhar.todo.service.UserService;

@Model
public class TodoListController {

	@Inject
	private TodoListService todoListService;

	@Inject
	UserService userService;

	@Inject
	UserRepository userRepository;

	@Produces
	@Named
	private TodoList newTodoList;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void initNewTodoList() {
		newTodoList = new TodoList();
	}

	public void createNewTodoList(String email) {
		try {
			System.out.println("Finding User with " + email);
			User user = userRepository.findByEmail(email);
			System.out.println("Found User " + user);
			if (user == null) {
				throw new RuntimeException("No user found with email " + email);
			}
			newTodoList.setCreatedBy(user);
			System.out.println("Persisting todo list");
			todoListService.create(newTodoList);
			System.out.println("created todo list" + newTodoList);
			user.getTodoLists().add(newTodoList);
			userService.update(user);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Created!", "TodoList creation successful");
			facesContext.addMessage(null, m);
			initNewTodoList();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "TodoList creation unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Todolist creation failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}
}
