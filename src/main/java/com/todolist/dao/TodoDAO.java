package com.todolist.dao;

import com.todolist.exception.NotFoundException;
import com.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class TodoDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    public TodoDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Todo> findAll() {
        return mongoTemplate.findAll(Todo.class);
    }

    public Todo addOne(Todo todo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date createDate = new Date();
        todo.setCreateDate(sdf.format(createDate));
        return mongoTemplate.save(todo);
    }

    public Todo updateById(String id, Todo updateTodo) throws NotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("status", updateTodo.getStatus());
        mongoTemplate.updateFirst(query, update, Todo.class);
        System.out.println(mongoTemplate.findById(id, Todo.class).getStatus());
        return mongoTemplate.findById(id, Todo.class);
    }

    public Boolean deleteById(String id) throws NotFoundException {
        mongoTemplate.remove(Objects.requireNonNull(mongoTemplate.findById(id, Todo.class)));
        return mongoTemplate.findById(id, Todo.class) == null;
    }

    public Todo findById(String id) {
        return mongoTemplate.findById(id, Todo.class);
    }

    public List<Todo> findAllByPage(Integer page, Integer pageSize) {
        Query query = new Query();
        query.skip(page).limit(pageSize);
        return mongoTemplate.find(query, Todo.class);
    }
}
