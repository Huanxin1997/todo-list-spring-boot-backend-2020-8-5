package com.thoughtworks.mapper;

import com.thoughtworks.dto.TodoRequest;
import com.thoughtworks.dto.TodoResponse;
import com.thoughtworks.model.Todo;
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
