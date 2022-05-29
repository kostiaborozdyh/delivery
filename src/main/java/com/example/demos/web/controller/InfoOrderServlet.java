package com.example.demos.web.controller;

import com.example.demos.model.dao.OrderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InfoOrderServlet", value = "/info")
public class InfoOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Integer orderId = Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("infoOrder", OrderDao.getOrder(orderId));
        if (request.getSession().getAttribute("role").equals("user")) {
            response.sendRedirect("/user/userOrder.jsp");
        } else {
            response.sendRedirect("/man/orderUser.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
