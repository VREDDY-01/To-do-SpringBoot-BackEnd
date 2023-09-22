package com.vishnu.todo.repo;

import com.vishnu.todo.entities.Todo;
import com.vishnu.todo.entities.Type;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepo extends CrudRepository<Todo,Integer> {
	List<Todo> findByTodoStatusEquals(boolean flag);

	List<Todo> findByCreatedAtGreaterThanEqualAndTodoStatusEquals(LocalDateTime dateTime, boolean flag);

	List<Todo> findByTodoTagIsAndTodoStatusIs(Type type, boolean flag);

	@Modifying
	@Query(value = "UPDATE TODO SET TODO_STATUS = :flag WHERE TODO_TAG = :type",nativeQuery = true)
	void updateStatusWithType(String type, boolean flag);
}
