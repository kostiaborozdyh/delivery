package com.example.demos.web.controller;

import com.example.demos.model.utils.FiltrationOrder;
import com.example.demos.model.dao.OrderDao;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "FiltrationOrderServlet", value = "/filtrationOrder")
public class FiltrationOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String[] paymentStatus = request.getParameterValues("paymentStatus");
        String[] location = request.getParameterValues("locationStatus");
        String minDateCreate = request.getParameter("minDateCreate");
        String maxDateCreate = request.getParameter("maxDateCreate");
        String minDateOfArrival = request.getParameter("minDateOfArrival");
        String maxDateOfArrival = request.getParameter("maxDateOfArrival");
        String sort = request.getParameter("sort");
        Set<String> cityFrom = null;
        Set<String> cityTo = null;
        if (request.getParameterValues("cityFrom[]") != null)
            cityFrom = new HashSet<>(Arrays.asList(request.getParameterValues("cityFrom[]")));
        if (request.getParameterValues("cityTo[]") != null)
            cityTo = new HashSet<>(Arrays.asList(request.getParameterValues("cityTo[]")));
        if (cityFrom == null && session.getAttribute("cityFrom") != null)
            cityFrom = (Set<String>) session.getAttribute("cityFrom");
        if (cityTo == null && session.getAttribute("cityTo") != null)
            cityTo = (Set<String>) session.getAttribute("cityTo");
        session.setAttribute("minPrice", minPrice);
        session.setAttribute("maxPrice", maxPrice);
        if (paymentStatus != null) {
            if (Arrays.asList(paymentStatus).contains("На розгляді")) session.setAttribute("status1", "status1");
            if (Arrays.asList(paymentStatus).contains("Очікує оплату")) session.setAttribute("status2", "status2");
            if (Arrays.asList(paymentStatus).contains("Оплачено")) session.setAttribute("status3", "status3");
        }
        if (location != null) {
            if (Arrays.asList(location).contains("В місті відправлення"))
                session.setAttribute("location1", "location1");
            if (Arrays.asList(location).contains("В дорозі")) session.setAttribute("location2", "location2");
            if (Arrays.asList(location).contains("У відділенні пошти")) session.setAttribute("location3", "location3");
            if (Arrays.asList(location).contains("Отримано")) session.setAttribute("location4", "location4");
        }
        session.setAttribute("minDateCreate", minDateCreate);
        session.setAttribute("maxDateCreate", maxDateCreate);
        session.setAttribute("minDateOfArrival", minDateOfArrival);
        session.setAttribute("maxDateOfArrival", maxDateOfArrival);
        session.setAttribute("cityFrom", cityFrom);
        session.setAttribute("cityTo", cityTo);
        session.setAttribute("sort", sort);
        List<Order> orderList;
        if (session.getAttribute("role").equals("user"))
            orderList = OrderDao.getUserOrders((User) session.getAttribute("user"));
        else orderList = OrderDao.getOrderList();
        orderList = FiltrationOrder.price(minPrice, maxPrice, orderList);
        orderList = FiltrationOrder.paymentStatus(paymentStatus, orderList);
        orderList = FiltrationOrder.location(location, orderList);
        orderList = FiltrationOrder.dateCreate(minDateCreate, maxDateCreate, orderList);
        orderList = FiltrationOrder.dateOfArrival(minDateOfArrival, maxDateOfArrival, orderList);
        orderList = FiltrationOrder.cityFrom(cityFrom, orderList);
        orderList = FiltrationOrder.cityTo(cityTo, orderList);
        if (sort != null) {
            orderList = FiltrationOrder.sorting(sort, orderList);
        }
        session.removeAttribute("pageNumberOrder");
        session.setAttribute("orders", orderList);
        if (session.getAttribute("role").equals("user")) {
            response.sendRedirect("/user/order.jsp");
        } else {
            response.sendRedirect("/man/orderList.jsp");
        }
    }
}
