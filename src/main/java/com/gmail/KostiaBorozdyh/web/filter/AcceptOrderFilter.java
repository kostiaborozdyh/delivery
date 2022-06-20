package com.gmail.KostiaBorozdyh.web.filter;

import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AcceptOrderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String city = (String) session.getAttribute("city");

        if (city == null) {
            response.sendRedirect("/employee/ordersTable.jsp");
        } else {
            List<Order> orderList = OrderService.getOrderListOnRecord(city);
            request.getSession().setAttribute("orderList", orderList);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
