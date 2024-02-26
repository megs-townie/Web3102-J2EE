package com.todolist.todo.database;

import com.todolist.todo.dao.TaskDAO;
import com.todolist.todo.model.Task;

import static com.todolist.todo.database.SQLConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDB implements TaskDAO {
    private static final String SQL_SELECT = "SELECT * FROM task_list";
    private static final String SQL_INSERT = "INSERT INTO task_list(task_name, task_status, due_date) VALUES (?,'In Progress',?)";
    private static final String SQL_UPDATE = "UPDATE task_list SET task_status='Completed' WHERE task_id = ?";
    private static final String SQL_DELETE = "DELETE FROM task_list WHERE task_id = ?";

    // Insert the methods from the TaskDAO here
    @Override
    public void insert(Task task) throws SQLException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // Attempt some data insertion
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);


            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setDate(2, (Date) task.getDueDate());

            preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("SQL Syntax error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        } finally {
            // Close the connection to DB and prepared statement
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void update(int taskId) throws SQLException {
        // Pre-requisites for a DB connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("SQL Syntax error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        } finally {
            // Close the connection to DB and prepared statement
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(int taskId) throws SQLException {
        // Pre-requisites for a DB connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("SQL Syntax error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the connection to DB and prepared statement
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Task> select() throws SQLException {
        // Pre-requisites for a DB connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Task> tasks = new ArrayList<>();

        try {
            connection = SQLConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            // While there is a next option in the resultSet
            while (resultSet.next()) {
                tasks.add(new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("status")
                ));
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return tasks;
    }
}