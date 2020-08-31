package com.todolist.exception;

public enum ExceptionMessage {
    NOT_FOUND_MESSAGE("Not Found this TODO");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
