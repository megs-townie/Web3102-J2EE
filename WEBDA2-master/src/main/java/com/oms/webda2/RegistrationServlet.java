package com.oms.webda2;

import jakarta.servlet.RequestDispatcher;
import org.mindrot.jbcrypt.BCrypt;

import com.oms.webda2.DAO.UserDAO;
import com.oms.webda2.controller.UserController;
import com.oms.webda2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    private UserDAO user;

    @Override
    public void init() throws ServletException {
        super.init();
        user = new UserController();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserController();

        try {
            List<User> users = userDAO.select();
            request.setAttribute("users", users);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        if (path.equals("/registration")) {
            registerUser(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String postalCode = request.getParameter("postalCode");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        try {
            // Check for duplicate email
            if (user.emailExists(email)) {
                request.setAttribute("duplicateEmail", true);
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                return;
            }

            // Check for password mismatch
            if (!password.equals(confirmPassword)) {
                request.setAttribute("passwordMismatch", true);
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                return;
            }

            // Hash password before sending to the DB
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setAddress(address);
            newUser.setCity(city);
            newUser.setProvince(province);
            newUser.setPostalCode(postalCode);
            newUser.setEmail(email);
            newUser.setPassword(hashedPassword);

            user.insert(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("products.jsp");
    }
}
