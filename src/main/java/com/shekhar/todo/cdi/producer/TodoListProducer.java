package com.shekhar.todo.cdi.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.shekhar.todo.cdi.repository.TodoListRepository;
import com.shekhar.todo.domain.TodoList;


@RequestScoped
public class TodoListProducer {

    @Inject
    private TodoListRepository todoListRepository;

    private List<TodoList> todolists;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<TodoList> getTodolists() {
        return todolists;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TodoList TodoList) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        todolists = todoListRepository.findAllTodoListsOrderedByCreationDate();
    }
}
