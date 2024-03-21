package com.oms.webda2.DAO;

import com.oms.webda2.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void insert(User user) throws SQLException;
    User getUserSessionInfo(String email) throws SQLException;
    List<User> select() throws SQLException;
    boolean emailExists(String email) throws SQLException;
}
