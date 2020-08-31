package com.todolist.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Todo {
    private String id;
    private String content;
    private Boolean status = false;
    @Field("create_date")
    private String createDate;

    public Todo() { }

    public Todo(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
