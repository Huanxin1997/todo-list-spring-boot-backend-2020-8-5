package com.thoughtworks;

import com.thoughtworks.exception.NotFoundException;
import com.thoughtworks.repository.TodoRepository;
import com.thoughtworks.model.Todo;
import com.thoughtworks.service.TodoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class TodoServiceTest {
    List<Todo> todos = new ArrayList<>();

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;
    private Todo createdTodo;

    @BeforeAll
    void initTodos(){
        todos.add(new Todo("Hans"));
        todos.add(new Todo("Andy"));
    }

    @Test
    void should_return_todos_when_find_all_todos_given_none() {
        //given
        when(todoRepository.findAll()).thenReturn(todos);

        //when
        List<Todo> actualTodos = todoService.findAll();

        //then
        assertEquals(todos.get(0), actualTodos.get(0));
        assertEquals(todos.get(1), actualTodos.get(1));
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() {
        //given
        Todo todo = new Todo("Hans");
        when(todoRepository.save(todo)).thenReturn(todo);

        //when
        createdTodo = todoService.addOne(todo);

        //then
        assertNotNull(createdTodo);
        assertEquals(todo.getContent(), createdTodo.getContent());
    }

    @Test
    void should_return_todo_when_update_todo_given_todo() throws NotFoundException {
        //given
        Todo todo = new Todo("Hans");
        when(todoRepository.save(todo)).thenReturn(todo);
        Todo createdTodo = todoRepository.save(todo);
        when(todoRepository.findById(createdTodo.getId())).thenReturn(java.util.Optional.of(createdTodo));

        //when
        createdTodo.setStatus(true);
        Todo updatedTodo = todoService.updateById(createdTodo.getId(), createdTodo);

        //then
        assertNotNull(updatedTodo);
        assertEquals(createdTodo, updatedTodo);
        assertEquals(createdTodo.getStatus(), updatedTodo.getStatus());
    }

    @Test
    void should_return_true_when_delete_todo_given_id() throws NotFoundException {
        //given
        Todo todo = new Todo("Hans");
        when(todoRepository.save(todo)).thenReturn(todo);
        Todo createdTodo = todoRepository.save(todo);

        //when
        Boolean result = todoService.deleteById(createdTodo.getId());

        //then
        assertTrue(result);
    }
}
