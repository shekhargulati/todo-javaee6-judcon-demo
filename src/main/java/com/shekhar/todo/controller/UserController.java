package com.shekhar.todo.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.shekhar.todo.domain.User;
import com.shekhar.todo.service.UserService;

@Model
public class UserController {

	@Inject
	private UserService userRegisterationService;
	
	@Produces
    @Named
    private User newUser;
	
	@Inject
	private FacesContext facesContext;

    @PostConstruct
    public void initNewUser() {
        newUser = new User();
    }
    
    public void registerUser(){
    	 try {
             userRegisterationService.register(newUser);
             FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
             facesContext.addMessage(null, m);
             initNewUser();
         } catch (Exception e) {
             String errorMessage = getRootErrorMessage(e);
             FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
             facesContext.addMessage(null, m);
         }
    }
    
    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
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
