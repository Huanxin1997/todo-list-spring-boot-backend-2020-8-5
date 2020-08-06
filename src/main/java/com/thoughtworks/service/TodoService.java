package com.thoughtworks.service;

import com.thoughtworks.repository.TodoRepository;
import com.thoughtworks.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    public Todo addOne(Todo todo) {
        return todoRepository.save(todo);
    }
}
