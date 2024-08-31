/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Database;
import model.User;
import model.UserDAO;

/**
 *
 * @author Nauh
 */
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        Connection connection = null;
        try {
            connection = Database.getConnection(); // Your database connection method
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        userDAO = new UserDAO(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        if (user != null && user.getPassword().equals(currentPassword) && validatePassword(newPassword)) {
            try {
                boolean updated = userDAO.updateUserPassword(user.getId(), newPassword);
                if (updated) {
                    request.setAttribute("success", "Password changed successfully.");
                } else {
                    request.setAttribute("error", "Failed to change password.");
                }
            } catch (SQLException e) {
                request.setAttribute("error", "Database error.");
            }
        } else {
            request.setAttribute("error", "Invalid current password or new password doesn't meet criteria.");
        }

        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$&*]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
