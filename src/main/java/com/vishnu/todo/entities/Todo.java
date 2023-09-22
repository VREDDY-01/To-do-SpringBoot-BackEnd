package com.vishnu.todo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {
	@Id
	private Integer todoId;

	@NotBlank
	private String todoDesc;

	private boolean todoStatus;

	@Enumerated(EnumType.STRING)
	private Type todoTag;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
