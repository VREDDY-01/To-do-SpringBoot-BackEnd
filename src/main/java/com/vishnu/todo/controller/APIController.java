package com.vishnu.todo.controller;

import com.vishnu.todo.entities.Todo;
import com.vishnu.todo.entities.Type;
import com.vishnu.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
public class APIController {
	@Autowired
	TodoService todoService;

	//GET REQUEST
	@GetMapping("todos")
	public List<Todo> getTodoList(){
		return todoService.getTodoList();
	}

	//POST REQUEST
	@PostMapping("todo")
	public String postTodo(@RequestBody @Valid Todo item){
		return todoService.addItem(item);
	}

	//PUT REQUEST
	@PutMapping("todos/update/{id}")
	public String updateTodoItem(@PathVariable Integer id,@RequestParam boolean flag){
		return todoService.updateItem(id,flag);
	}

	//DELETE REQUEST
	@DeleteMapping("todos/delete/{id}")
	public String deleteItem(@PathVariable Integer id){
		return todoService.deleteItem(id);
	}

	//-------------------------------------------------------
	//Add List of Todos
	@PostMapping("todos/addAll")
	public String addMultipleItems(@RequestBody List<Todo> todoItems){
		return todoService.addItems(todoItems);
	}

	//Get All done at a time
	@PutMapping("todos/updateAll")
	public String updateAll(@RequestParam boolean flag){
		return todoService.updateAll(flag);
	}

	//Delete All Items
	@DeleteMapping("todos/deleteAll")
	public String deleteAllItems(){
		return todoService.deleteAll();
	}

	//get all undone to-do items
	@GetMapping("todos/undone")
	public List<Todo> getUndone(){
		boolean flag = false;
		return todoService.getAllUndone(flag);
	}

	//delete a list of Items
	@DeleteMapping("todos/deleteItems")
	public List<Todo> deleteItems(@RequestBody List<Integer> ids){
		return todoService.deleteAList(ids);
	}

	@GetMapping("todos/time/{dateTime}/done")
	public List<Todo> getAllItemsLessTimeAndIsTrue(@PathVariable LocalDateTime dateTime){
		boolean flag = true;
		return todoService.getItemsLessTimeAndTrue(dateTime,flag);
	}

	@GetMapping("todos/type/status")
	public List<Todo> getAllItemsTodoTypeAndIsFalse(@RequestParam Type type){
		boolean flag = true;
		return todoService.getItemsByTodoTypeAndIsFalse(type,flag);
	}

	@PutMapping("todos/status/type")
	public String updateItemsStatusWithType(@RequestParam Type type,@RequestParam boolean flag){
		return todoService.updateItemsStatusWithType(type,flag);
	}
}
