package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logoutservlet"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Huỷ bỏ session hiện tại
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Huỷ bỏ session
        }
        
        // Chuyển hướng người dùng về trang đăng nhập
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Xử lý cả yêu cầu POST và GET
    }

    @Override
    public String getServletInfo() {
        return "Servlet to handle user logout";
    }
}
