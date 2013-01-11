package com.shekhar.todo.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.shekhar.todo.cdi.repository.UserRepository;
import com.shekhar.todo.domain.TodoItem;
import com.shekhar.todo.service.TodoListService;
import com.shekhar.todo.service.UserService;

@Model
public class TodoItemController {

	@Inject
	private TodoListService todoListService;

	@Inject
	UserService userService;

	@Inject
	UserRepository userRepository;

	@Produces
	@Named
	private TodoItem newTodoItem;

	@Inject
	private FacesContext facesContext;
	

	@PostConstruct
	public void initNewTodoItem() {
		newTodoItem = new TodoItem();
	}

	public void addTodo(Long todoListId) {
		try {
			todoListService.addTodo(todoListId, newTodoItem);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Created!", "TodoItem creation successful");
			facesContext.addMessage(null, m);
			initNewTodoItem();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "TodoItem unsuccessful");
			facesContext.addMessage(null, m);
		}
	}
	
	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "TodoItem creation failed. See server log for more information";
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
