package com.vishnu.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class APIController {
	@Autowired
	List<Todo> todoList;

	//GET REQUEST
	@GetMapping("todos")
	public List<Todo> getTodoList(){
		return todoList;
	}

	//POST REQUEST
	@PostMapping("todos/new")
	public String postTodo(@RequestBody Todo item){
		todoList.add(item);
		return "Todo item added to the data";
	}

	//PUT REQUEST
	@PutMapping("todos/update/{id}")
	public String updateTodoItem(@PathVariable Integer id,@RequestParam boolean flag){
		for (Todo todo:todoList) {
			if (todo.getTodoId()==id){
				todo.setStatus(flag);
				return "todo with id: "+id+" is updated!!";
			}
		}
		return "Invalid Id or object with that id doesn't exist";
	}

	//DELETE REQUEST
	@DeleteMapping("todos/delete/{id}")
	public String deleteItem(@PathVariable Integer id){
		for (Todo todo:todoList) {
			if (todo.getTodoId()==id){
				todoList.remove(todo);
				return "todo with id: "+id+" is deleted!!";
			}
		}
		return "Invalid Id or object with that id doesn't exist";
	}

	//-------------------------------------------------------
	//Add List of Todos
	@PostMapping("todos/addAll")
	public String addMultipleItems(@RequestBody Todo[] todoItems){
		Collections.addAll(todoList, todoItems);
		return "all Item added to the list";
	}

	//Get All done at a time
	@PutMapping("todos/updateAll")
	public String updateAll(@RequestParam boolean flag){
		for (Todo todoItem:todoList) {
			todoItem.setStatus(flag);
		}
		return "All task completed!!";
	}

	//Delete All Items
	@DeleteMapping("todos/deleteAll")
	public String deleteAllItems(){
		todoList.clear();
		return "All task deleted!!";
	}
}
