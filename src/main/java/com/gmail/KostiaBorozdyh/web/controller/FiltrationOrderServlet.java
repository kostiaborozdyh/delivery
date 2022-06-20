package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.FilterOrder;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.utils.FiltrationOrder;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FiltrationOrderServlet", value = "/filtrationOrder")
public class FiltrationOrderServlet extends HttpServlet {
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

        User user =(User) session.getAttribute("user");
        boolean userHasRoleUser = user.getRoleId() == 1;
        List<Order> orderList = OrderService.getOrderListByUser(user,userHasRoleUser);

        orderList = FiltrationOrder.doFilter(orderList,filterOrder);

        session.setAttribute("filter",filterOrder);
        session.setAttribute("orders", orderList);
        session.removeAttribute("pageNumberOrder");

        if (userHasRoleUser) {
            response.sendRedirect("/user/order.jsp");
        } else {
            response.sendRedirect("/man/orderList.jsp");
        }

    }
}
