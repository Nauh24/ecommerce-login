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
import model.Database;
import model.User;
import model.UserDAO;

/**
 *
 * @author Nauh
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        Connection connection = null;
        try {
            connection = Database.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        userDAO = new UserDAO(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Kiểm tra mật khẩu có hợp lệ không
        if (!isValidPassword(password)) {
            request.setAttribute("error", "Password must be at least 8 characters long, contain at least one numeric digit, one capital letter, and one special character (#, @, $, &, *).");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password); // Bạn có thể mã hóa mật khẩu trước khi lưu

        try {
            if (userDAO.saveUser(user)) {
                // Nếu đăng ký thành công, lưu thông báo thành công vào session
                HttpSession session = request.getSession();
                session.setAttribute("successMessage", "Registration successful! You can now log in.");
                response.sendRedirect("login.jsp");
            } else {
                // Nếu đăng ký thất bại, gửi lại thông báo lỗi và quay lại trang đăng ký
                request.setAttribute("error", "Registration failed! Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

// Phương thức kiểm tra mật khẩu có hợp lệ hay không
    private boolean isValidPassword(String password) {
        // Mật khẩu phải có ít nhất 8 ký tự, một chữ số, một chữ cái viết hoa và một ký tự đặc biệt
       String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()~`\\-_=+/?\\\\|<>,.;:'\\[\\]{}]).{8,}$";
        return password.matches(passwordPattern);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
