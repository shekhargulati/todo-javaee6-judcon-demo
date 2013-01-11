package com.shekhar.todo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TodoList implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 10, max = 100)
	private String name;

	@OneToMany
	@OrderColumn
	@JoinColumn(name = "todolist_id")
	private Set<TodoItem> todoItems;

	private Date createdOn = new Date();

	@ManyToOne
	@NotNull
	private User createdBy;

	public TodoList() {
		// TODO Auto-generated constructor stub
	}

	public TodoList(String name, Date createdOn) {
		this.createdOn = createdOn;
		this.name = name;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setTodoItems(Set<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}

	public Set<TodoItem> getTodoItems() {
		return todoItems;
	}

	@Override
	public String toString() {
		return "TodoList [id=" + id + ", name=" + name + ", createdOn="
				+ createdOn + "]";
	}

}
