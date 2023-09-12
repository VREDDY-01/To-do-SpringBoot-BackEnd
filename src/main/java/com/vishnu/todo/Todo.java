package com.vishnu.todo;

public class Todo {
	private int todoId;
	private String todoDesc;
	private boolean status;

	public Todo(int todoId, String todoDesc, boolean status) {
		this.todoId = todoId;
		this.todoDesc = todoDesc;
		this.status = status;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getTodoDesc() {
		return todoDesc;
	}

	public void setTodoDesc(String todoDesc) {
		this.todoDesc = todoDesc;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
