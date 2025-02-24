package org.example.filterpractice.todo.repository;

import org.example.filterpractice.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
