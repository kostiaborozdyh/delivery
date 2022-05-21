package com.example.demos.web.controller;

import com.example.demos.model.FiltrationOrder;
import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "FiltrationOrderServlet", value = "/filtrationOrder")
public class FiltrationOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String[] paymentStatus = request.getParameterValues("paymentStatus");
        String minDateCreate = request.getParameter("minDateCreate");
        String maxDateCreate = request.getParameter("maxDateCreate");
        String minDateOfArrival = request.getParameter("minDateOfArrival");
        String maxDateOfArrival = request.getParameter("maxDateOfArrival");
        String[] cityFrom = request.getParameterValues("cityFrom[]");
        String[] cityTo = request.getParameterValues("cityTo[]");
        String sort = request.getParameter("sort");
        HttpSession session = request.getSession();
        session.setAttribute("minPrice",minPrice);
        session.setAttribute("maxPrice",maxPrice);
        if(paymentStatus!=null) {
            if (Arrays.asList(paymentStatus).contains("На розгляді")) session.setAttribute("status1", "status1");
            if (Arrays.asList(paymentStatus).contains("Очікує оплату")) session.setAttribute("status2", "status2");
            if (Arrays.asList(paymentStatus).contains("Оплачено")) session.setAttribute("status3", "status3");
        }
        session.setAttribute("minDateCreate",minDateCreate);
        session.setAttribute("maxDateCreate",maxDateCreate);
        session.setAttribute("minDateOfArrival",minDateOfArrival);
        session.setAttribute("maxDateOfArrival",maxDateOfArrival);
        session.setAttribute("cityFrom",cityFrom);
        session.setAttribute("cityTo",cityTo);
        session.setAttribute("sort",sort);
        List<Order> orderList;
        if (session.getAttribute("role").equals("user"))
           orderList = OrderDao.getUserOrders((User)session.getAttribute("user"));
        else orderList = OrderDao.getOrderList();
        orderList = FiltrationOrder.price(minPrice,maxPrice,orderList);
        orderList = FiltrationOrder.paymentStatus(paymentStatus,orderList);
        orderList = FiltrationOrder.dateCreate(minDateCreate,maxDateCreate,orderList);
        orderList = FiltrationOrder.dateOfArrival(minDateOfArrival,maxDateOfArrival,orderList);
        orderList = FiltrationOrder.cityFrom(cityFrom,orderList);
        orderList = FiltrationOrder.cityTo(cityTo,orderList);
        if (sort!=null)
            orderList = FiltrationOrder.sorting(sort,orderList);
        if (session.getAttribute("role").equals("user")) {
            session.setAttribute("orders",orderList);
            response.sendRedirect("/user/order.jsp");
        }
        else {
            session.setAttribute("orderList",orderList);
            response.sendRedirect("/man/orderList.jsp");
        }
    }
}
