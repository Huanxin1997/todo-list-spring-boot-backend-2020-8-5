package com.todolist.controller;

import com.todolist.exception.NotFoundException;
import com.todolist.model.Todo;
import com.todolist.service.TodoService;
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
    public List<Todo> getAllTodos(Integer page, Integer pageSize) {
        if (page != null || pageSize != null) {
            return todoService.findAllByPage(page, pageSize);
        }
        return todoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Todo getAllTodos(@PathVariable String id) {
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addOne(todo);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Todo updateTodo(@PathVariable String id, @RequestBody Todo todo) throws NotFoundException {
        return todoService.updateById(id, todo);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteTodo(@PathVariable String id) throws NotFoundException {
        Boolean result = todoService.deleteById(id);
        return result == true ? "Delete success" : "Delete fail";
    }
}
