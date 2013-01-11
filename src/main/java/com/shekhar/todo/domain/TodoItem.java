package com.shekhar.todo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TodoItem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String task;

	private Date createdOn = new Date();

	private Date dueOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDueOn() {
		return dueOn;
	}

	public void setDueOn(Date dueOn) {
		this.dueOn = dueOn;
	}

	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", task=" + task + ", createdOn="
				+ createdOn + "]";
	}

}
