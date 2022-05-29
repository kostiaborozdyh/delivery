package com.example.demos.web.controller;

import com.example.demos.model.dao.OrderDao;
import com.example.demos.model.entity.Order;
import com.example.demos.model.utils.Calculate;
import com.example.demos.model.utils.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChooseCityServlet", value = "/chooseCity")
public class ChooseCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String on = request.getParameter("on");
        if(on!=null) {
            request.getSession().removeAttribute("pageNumberOrder");
        }
        String city;
        if (request.getSession().getAttribute("city") == null) {
            city = request.getParameter("city");
            city = JsonParser.cutName(city);
            request.getSession().setAttribute("city", city);
        } else {
            city = (String) request.getSession().getAttribute("city");
        }
        List<Order> orderList = OrderDao.getOrderList(city);
        if (request.getSession().getAttribute("pageNumberOrder") == null) {
            List<Integer> list = Calculate.getPaginationList(orderList);
            if (list == null) {
                request.getSession().setAttribute("shortOrders", orderList);
            } else {
                request.getSession().setAttribute("shortOrders", Calculate.getFiveElements(orderList, 1));
            }
            request.getSession().setAttribute("listNumberOrder", list);
            request.getSession().setAttribute("pageNumberOrder", 1);
        }

        request.getSession().setAttribute("orders", orderList);
        response.sendRedirect("/employee/ordersTable.jsp");
    }
}
