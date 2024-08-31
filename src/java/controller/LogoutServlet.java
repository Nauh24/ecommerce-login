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

/**
 *
 * @author Nauh
 */
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
