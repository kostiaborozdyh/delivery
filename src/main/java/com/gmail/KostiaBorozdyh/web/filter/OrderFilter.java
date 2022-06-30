package com.gmail.KostiaBorozdyh.web.filter;

import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Filter for Order List
 */
public class OrderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        List<Order> orderList = (List<Order>) session.getAttribute("orders");

        if (orderList == null) {
            User user = (User) request.getSession().getAttribute("user");
            boolean userHasRoleUser = user.getRoleId() == 1;
            orderList = OrderService.getOrderListByUser(user, userHasRoleUser);
        }

        Set<String> cityFromSet = Calculate.cityFromSet(orderList);
        Set<String> cityToSet = Calculate.cityToSet(orderList);
        session.setAttribute("cityFromSet", cityFromSet);
        session.setAttribute("cityToSet", cityToSet);

        if (session.getAttribute("pageNumberOrder") == null) {
            List<Integer> list = Calculate.getPaginationList(orderList);
            if (list == null) {
                session.setAttribute("shortOrders", orderList);
            } else {
                session.setAttribute("shortOrders", Calculate.getFiveElements(orderList, 1));
            }
            session.setAttribute("listNumberOrder", list);
            session.setAttribute("pageNumberOrder", 1);
        }

        session.setAttribute("orders", orderList);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

