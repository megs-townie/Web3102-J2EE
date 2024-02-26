package com.todolist.todo.dao;

import com.todolist.todo.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    void insert(Task task) throws SQLException;
    void update(int taskId) throws SQLException;
    void delete(int taskId) throws SQLException;
    List<Task> select() throws SQLException;
}
