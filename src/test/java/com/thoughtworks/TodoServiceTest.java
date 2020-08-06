package com.thoughtworks;

import com.thoughtworks.repository.TodoRepository;
import com.thoughtworks.entity.Todo;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class TodoServiceTest {
    List<Todo> todos = new ArrayList<>();

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

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
}
