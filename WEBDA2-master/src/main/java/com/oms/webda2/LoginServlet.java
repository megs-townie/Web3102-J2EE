package com.oms.webda2;

import com.oms.webda2.DAO.UserDAO;
import com.oms.webda2.controller.UserController;
import com.oms.webda2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginServlet extends HttpServlet {
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
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        if (path.equals("/login")) {
            loginUser(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User loggedInUser = user.getUserSessionInfo(email);

            if (loggedInUser != null && BCrypt.checkpw(password, loggedInUser.getPassword())) {
                // Passwords & email match: allow session to start
                HttpSession session = request.getSession();
                session.setAttribute("user", loggedInUser);

                // This block of code will handle dynamic redirects for when user is confirmed logged in
                String redirectURL = request.getParameter("redirect");

                if (redirectURL !=null && !redirectURL.isEmpty()) {
                    response.sendRedirect(redirectURL);
                } else {
                    // If no specific redirectURL, redirect to homepage
                    response.sendRedirect("products.jsp");
                }
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Lastly, if login fails, stay on login page
        request.setAttribute("loginFailed", true);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
