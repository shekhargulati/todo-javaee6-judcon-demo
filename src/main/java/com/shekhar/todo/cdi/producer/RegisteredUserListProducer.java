package com.shekhar.todo.cdi.producer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.shekhar.todo.cdi.repository.UserRepository;
import com.shekhar.todo.domain.User;

import java.util.List;


@RequestScoped
public class RegisteredUserListProducer {

    @Inject
    private UserRepository userRepository;

    private List<User> users;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<User> getUsers() {
        return users;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final User user) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        users = userRepository.findAllUsersOrderedByRegisterationDate();
    }
}
