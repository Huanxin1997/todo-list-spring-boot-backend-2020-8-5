package com.todolist.service;

import com.todolist.dao.TodoDAO;
import com.todolist.exception.NotFoundException;
import com.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public List<Todo> findAll() {
        return todoDAO.findAll();
    }

    public Todo addOne(Todo todo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date createDate = new Date();
        todo.setCreateDate(sdf.format(createDate));
        return todoDAO.addOne(todo);
    }

    public Todo updateById(String id, Todo updateTodo) throws NotFoundException {
        return todoDAO.updateById(id, updateTodo);
    }

    public Boolean deleteById(String id) throws NotFoundException {
        return todoDAO.deleteById(id);
    }

    public Todo findById(String id) {
        return todoDAO.findById(id);
    }

    public List<Todo> findAllByPage(Integer page, Integer pageSize) {
        int start = (page - 1) * pageSize;
        return todoDAO.findAllByPage(start, pageSize);
    }
}
