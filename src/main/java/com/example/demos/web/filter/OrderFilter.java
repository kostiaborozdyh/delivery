package com.example.demos.web.filter;

import com.example.demos.model.dao.OrderDao;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;
import com.example.demos.model.utils.Calculate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class OrderFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        List<Order> orderList = (List<Order>) request.getSession().getAttribute("orders");
        if (orderList == null) {
            if (request.getSession().getAttribute("role").equals("user")) {
                orderList = OrderDao.getUserOrders((User) request.getSession().getAttribute("user"));
            } else {
                orderList = OrderDao.getOrderList();
            }
        }
        Set<String> cityFromSet = Calculate.cityFromSet(orderList);
        Set<String> cityToSet = Calculate.cityToSet(orderList);
        request.getSession().setAttribute("cityFromSet", cityFromSet);
        request.getSession().setAttribute("cityToSet", cityToSet);
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
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

