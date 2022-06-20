package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PutOnRecordServlet", value = "/putOnRecord")
public class PutOnRecordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        OrderService.putOrderOnRecord(id);
        UserService.sendEmailByOrderId(id,2);
        response.sendRedirect("/employee/acceptOrder.jsp");
    }
}
