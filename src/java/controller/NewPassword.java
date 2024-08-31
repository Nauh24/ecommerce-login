/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
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
import model.Database;
import model.UserDAO;

/**
 *
 * @author Nauh
 */

@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection connection = Database.getConnection();
            userDAO = new UserDAO(connection);
        } catch (SQLException ex) {
            Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException("Database connection error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        RequestDispatcher dispatcher = null;

        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            String email = (String) session.getAttribute("email");

            try {
                boolean updated = userDAO.updatePasswordByEmail(email, newPassword);
                if (updated) {
                    request.setAttribute("status", "resetSuccess");
                    dispatcher = request.getRequestDispatcher("login.jsp");
                } else {
                    request.setAttribute("status", "resetFailed");
                    dispatcher = request.getRequestDispatcher("login.jsp");
                }
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, e);
                request.setAttribute("status", "resetFailed");
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("status", "passwordMismatch");
            dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}
