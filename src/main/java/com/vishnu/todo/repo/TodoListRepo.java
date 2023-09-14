package com.vishnu.todo.repo;


import com.vishnu.todo.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoListRepo {
	@Autowired
	private List<Todo> todoList;

	public List<Todo> getTodoList() {
		return todoList;
	}
}
