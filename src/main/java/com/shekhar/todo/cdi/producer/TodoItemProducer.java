package com.shekhar.todo.cdi.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.shekhar.todo.cdi.repository.TodoItemRepository;
import com.shekhar.todo.domain.TodoItem;


@RequestScoped
public class TodoItemProducer {

    @Inject
    private TodoItemRepository todoItemRepository;

    private List<TodoItem> todoItems;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final TodoItem todoItem) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        todoItems = todoItemRepository.findAllTodoItemsOrderedByCreationDate();
    }
}
