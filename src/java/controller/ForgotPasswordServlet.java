package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import model.Database;
import model.User;
import model.UserDAO;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgetpasswordservlet"})
public class ForgotPasswordServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = null;
        try {
            connection = Database.getConnection();
            userDAO = new UserDAO(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException("Database connection error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailOrPhone = request.getParameter("emailOrPhone");
        try {
            User user = userDAO.getUserByEmailOrPhone(emailOrPhone);
            if (user != null) {
                String resetToken = generateResetToken();
                // Lưu token vào cơ sở dữ liệu với thời gian hết hạn
                boolean tokenSaved = userDAO.saveResetToken(user.getId(), resetToken);
                
                if (tokenSaved) {
                    // Gửi email hoặc SMS với link reset password
                    String resetLink = generateResetLink(resetToken);
                    sendResetEmail(user.getEmail(), resetLink);

                    request.setAttribute("success", "Reset link has been sent to your email.");
                } else {
                    request.setAttribute("error", "Failed to generate reset token.");
                }
            } else {
                request.setAttribute("error", "User not found.");
            }
        } catch (SQLException e) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("error", "Database error.");
        }

        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }

    private String generateResetToken() {
        // Sinh token reset với độ dài 6 chữ số
        return Integer.toString(100000 + new Random().nextInt(900000));
    }

    private String generateResetLink(String token) {
        // Tạo link reset password với token
        return "http://yourdomain.com/resetPassword.jsp?token=" + token;
    }

    private void sendResetEmail(String recipientEmail, String resetLink) {
        // Implement email sending logic here
        // Ví dụ sử dụng JavaMail API:
        // EmailUtils.sendEmail(recipientEmail, "Password Reset", "Click the following link to reset your password: " + resetLink);
    }
}
