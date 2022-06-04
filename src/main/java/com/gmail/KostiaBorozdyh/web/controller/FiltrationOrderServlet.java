package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.FilterOrder;
import com.gmail.KostiaBorozdyh.model.utils.FiltrationOrder;
import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FiltrationOrderServlet", value = "/filtrationOrder")
public class FiltrationOrderServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(FiltrationOrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        FilterOrder filterOrder = new FilterOrder();

        filterOrder.setMinPrice(request.getParameter("minPrice"));
        filterOrder.setMaxPrice(request.getParameter("maxPrice"));
        filterOrder.setPaymentStatus(request.getParameterValues("paymentStatus"));
        filterOrder.setLocation(request.getParameterValues("locationStatus"));
        filterOrder.setMinDateCreate(request.getParameter("minDateCreate"));
        filterOrder.setMaxDateCreate(request.getParameter("maxDateCreate"));
        filterOrder.setMinDateOfArrival(request.getParameter("minDateOfArrival"));
        filterOrder.setMaxDateOfArrival(request.getParameter("maxDateOfArrival"));
        filterOrder.setCityFrom(request.getParameterValues("cityFrom[]"));
        filterOrder.setCityTo(request.getParameterValues("cityTo[]"));
        filterOrder.setSort(request.getParameter("sort"));

        List<Order> orderList;
        if (session.getAttribute("role").equals("user")) {
            orderList = OrderDao.getUserOrders((User) session.getAttribute("user"));
        } else {
            orderList = OrderDao.getOrderList();
        }

        orderList = FiltrationOrder.doFilter(orderList,filterOrder);
        session.setAttribute("filter",filterOrder);

        session.removeAttribute("pageNumberOrder");
        session.setAttribute("orders", orderList);

        if (session.getAttribute("role").equals("user")) {
            response.sendRedirect("/user/order.jsp");
        } else {
            response.sendRedirect("/man/orderList.jsp");
        }

    }
}
