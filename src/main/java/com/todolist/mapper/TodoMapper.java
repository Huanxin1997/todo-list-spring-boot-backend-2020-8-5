package com.todolist.mapper;

import com.todolist.dto.TodoRequest;
import com.todolist.dto.TodoResponse;
import com.todolist.model.Todo;
import org.springframework.beans.BeanUtils;

public class TodoMapper {
    public Todo toTodo(TodoRequest todoRequest){
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        return todo;
    }
    public TodoResponse toTodoResponse(Todo todo){
        TodoResponse responseTodo = new TodoResponse();
        BeanUtils.copyProperties(responseTodo, todo);
        return responseTodo;
    }
}
