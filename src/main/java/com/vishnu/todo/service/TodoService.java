package com.vishnu.todo.service;

import com.vishnu.todo.entities.Todo;
import com.vishnu.todo.repo.TodoListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoService {
	@Autowired
	TodoListRepo todoListRepo;

	public List<Todo> getTodoList() {
		return todoListRepo.getTodoList();
	}

	public String addItem(Todo item) {
		todoListRepo.getTodoList().add(item);
		return "Todo item added to the data";
	}

	public String updateItem(Integer id, boolean flag) {
		for (Todo todo:todoListRepo.getTodoList()) {
			if (todo.getTodoId()==id){
				todo.setStatus(flag);
				return "todo with id: "+id+" is updated!!";
			}
		}
		return "Invalid Id or object with that id doesn't exist";
	}

	public String deleteItem(Integer id) {
		for (Todo todo:todoListRepo.getTodoList()) {
			if (todo.getTodoId()==id){
				todoListRepo.getTodoList().remove(todo);
				return "todo with id: "+id+" is deleted!!";
			}
		}
		return "Invalid Id or object with that id doesn't exist";
	}

	public String addItems(Todo[] todoItems) {
		Collections.addAll(todoListRepo.getTodoList(), todoItems);
		return "all Item added to the list";
	}

	public String updateAll(boolean flag) {
		for (Todo todoItem:todoListRepo.getTodoList()) {
			todoItem.setStatus(flag);
		}
		return "All task completed!!";
	}

	public String deleteAll() {
		todoListRepo.getTodoList().clear();
		return "All task deleted!!";
	}

	public List<Todo> getAllUndone() {
		return todoListRepo.getTodoList().stream().filter((todo)->!todo.isStatus()).collect(Collectors.toList());
	}

	public List<Todo> deleteAList(List<Integer> ids) {
		for (Integer id : ids) {
			for (int j = 0; j < todoListRepo.getTodoList().size(); j++) {
				if (id.equals(todoListRepo.getTodoList().get(j).getTodoId())) {
					todoListRepo.getTodoList().remove(todoListRepo.getTodoList().get(j));
					break;
				}
			}
		}
		return todoListRepo.getTodoList();
	}
}
