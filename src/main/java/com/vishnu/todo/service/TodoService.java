package com.vishnu.todo.service;

import com.vishnu.todo.entities.Todo;
import com.vishnu.todo.entities.Type;
import com.vishnu.todo.repo.TodoRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
	@Autowired
	TodoRepo todoRepo;

	public List<Todo> getTodoList() {
		return (List<Todo>) todoRepo.findAll();
	}

	public String addItem(Todo item) {
		if (!todoRepo.existsById(item.getTodoId())){
			todoRepo.save(item);
			return "Added 1 item";
		}else{
			return "Item already found";
		}
	}

	public String updateItem(Integer id, boolean flag) {
		Todo todo = todoRepo.findById(id).orElse(null);
		if (todo!=null){
			todo.setTodoStatus(flag);
			todoRepo.save(todo);
			return "item updated";
		}
		return "item not found";
	}

	public String deleteItem(Integer id) {
		if (todoRepo.existsById(id)){
			todoRepo.deleteById(id);
			return "item deleted";
		}else{
			return "Item not found";
		}
	}

	public String addItems(List<Todo> todoItems) {
		todoRepo.saveAll(todoItems);
		return "added all items successfully";
	}


	public String updateAll(boolean flag) {
		todoRepo.findAll().forEach(todo -> {
			todo.setTodoStatus(flag);
			todoRepo.save(todo);
		});
		return "all items updated successfully";
	}

	public String deleteAll() {
		todoRepo.deleteAll();
		return "all items deleted";
	}

	public List<Todo> getAllUndone(boolean flag) {
		return todoRepo.findByTodoStatusEquals(flag);
	}

	public List<Todo> deleteAList(List<Integer> ids) {
		List<Todo> deletedList = (List<Todo>) todoRepo.findAllById(ids);
		todoRepo.deleteAllById(ids);
		return deletedList;
	}

	public List<Todo> getItemsLessTimeAndTrue(LocalDateTime dateTime, boolean flag) {
		return todoRepo.findByCreatedAtGreaterThanEqualAndTodoStatusEquals(dateTime,flag);
	}

	public List<Todo> getItemsByTodoTypeAndIsFalse(Type type, boolean flag) {
		return todoRepo.findByTodoTagIsAndTodoStatusIs(type,flag);
	}

	@Transactional
	public String updateItemsStatusWithType(Type type, boolean flag) {
		todoRepo.updateStatusWithType(type.name(),flag);
		return "updated";
	}
}
