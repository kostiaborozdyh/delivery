package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Sending email to user
 */
@WebServlet(name = "SendEmailOrderServlet", value = "/sendEmailOrder")
public class SendEmailOrderServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("idOrder"));

        UserService.sendEmailByOrderId(id, 3);
        response.sendRedirect("/user/order.jsp");
    }
}
