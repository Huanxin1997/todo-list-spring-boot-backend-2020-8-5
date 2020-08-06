package com.thoughtworks.service;

import com.thoughtworks.exception.ExceptionMessage;
import com.thoughtworks.exception.NotFoundException;
import com.thoughtworks.repository.TodoRepository;
import com.thoughtworks.model.Todo;
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

    public Todo updateById(int id, Todo updateTodo) throws NotFoundException {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null){
            throw new NotFoundException(ExceptionMessage.NOT_FOUND_MESSAGE);
        }
        todo.setStatus(updateTodo.getStatus());
        return todoRepository.save(todo);
    }

    public Boolean deleteById(int id) throws NotFoundException {
        todoRepository.findById(id);
        todoRepository.deleteById(id);
        return todoRepository.findById(id).isPresent();
    }
}
