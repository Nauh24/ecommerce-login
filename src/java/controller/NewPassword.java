package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
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
import model.UserDAO;

@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
    private UserDAO userDAO;

    // Biểu thức chính quy cho mật khẩu hợp lệ
    private static final String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()~`\\-_=+/?\\\\|<>,.;:'\\[{\\]}]).{8,}$";

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        RequestDispatcher dispatcher;

        // Kiểm tra mật khẩu mới có khớp với mật khẩu xác nhận không
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            // Kiểm tra mật khẩu có hợp lệ không
            if (isValidPassword(newPassword)) {
                String email = (String) session.getAttribute("email");

                try {
                    boolean updated = userDAO.updatePasswordByEmail(email, newPassword);
                    if (updated) {
                        // Mật khẩu đã được cập nhật thành công
                        request.setAttribute("status", "resetSuccess");
                        dispatcher = request.getRequestDispatcher("login.jsp");
                    } else {
                        // Lỗi khi cập nhật mật khẩu
                        request.setAttribute("status", "resetFailed");
                        dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
                    }
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, e);
                    request.setAttribute("status", "resetFailed");
                    dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                // Mật khẩu không hợp lệ
                request.setAttribute("errorMessage", "Mật khẩu phải có ít nhất 8 ký tự, bao gồm 1 chữ số, 1 ký tự viết hoa và 1 ký tự đặc biệt.");
                dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Mật khẩu không khớp
            request.setAttribute("errorMessage", "Mật khẩu không khớp. Vui lòng nhập lại.");
            dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Phương thức kiểm tra mật khẩu có hợp lệ không
    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
