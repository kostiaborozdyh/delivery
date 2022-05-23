package com.example.demos.web.controller;

import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChooseCityServlet", value = "/chooseCity")
public class ChooseCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city;
        if(request.getSession().getAttribute("city")==null) {
            city = request.getParameter("city");
            request.getSession().setAttribute("city",city);
        }
        else
            city = (String) request.getSession().getAttribute("city");
        List<Order> orderList = OrderDao.getOrderList(city);
        request.getSession().setAttribute("ordersTable",orderList);
        response.sendRedirect("/employee/ordersTable.jsp");
    }
}
