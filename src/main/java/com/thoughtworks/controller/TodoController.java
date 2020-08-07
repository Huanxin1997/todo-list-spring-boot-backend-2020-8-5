package com.thoughtworks.controller;

import com.thoughtworks.exception.NotFoundException;
import com.thoughtworks.model.Todo;
import com.thoughtworks.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addOne(todo);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Todo updateTodo(@PathVariable Integer id, @RequestBody Todo todo) throws NotFoundException {
        return todoService.updateById(id, todo);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Integer id) throws NotFoundException {
        todoService.deleteById(id);
    }
}
