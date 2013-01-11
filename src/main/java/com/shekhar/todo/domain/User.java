package com.shekhar.todo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "Users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Size(max = 10, min = 6)
	private String password;

	@NotNull
	private Date registeredOn = new Date();

	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TodoList> todoLists = new ArrayList<TodoList>();

	@ElementCollection
	@CollectionTable(name = "Hobbies", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "hobby")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> hobbies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public List<TodoList> getTodoLists() {
		return todoLists;
	}

	public void setTodoLists(List<TodoList> todoLists) {
		this.todoLists = todoLists;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", registeredOn=" + registeredOn + ", hobbies=" + hobbies
				+ "]";
	}

}
