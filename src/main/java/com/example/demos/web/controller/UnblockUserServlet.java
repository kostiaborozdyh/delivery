package com.example.demos.web.controller;

import com.example.demos.model.UserDao;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UnblockUserServlet", value = "/unblockUser")
public class UnblockUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            UserDao.unBlockUser(id);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/adm/usersTable.jsp");
    }
}
