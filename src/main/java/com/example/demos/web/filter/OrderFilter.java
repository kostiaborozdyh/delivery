package com.example.demos.web.filter;

import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;

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
    public void init(FilterConfig filterConfig)  {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getSession().getAttribute("orders")==null) {
            List<Order> orderList = OrderDao.getUserOrders((User) request.getSession().getAttribute("user"));
            Set<String> cityFromSet = OrderDao.cityFromSet(orderList);
            Set<String> cityToSet = OrderDao.cityToSet(orderList);
            request.getSession().setAttribute("orders", orderList);
            request.getSession().setAttribute("cityFromSet", cityFromSet);
            request.getSession().setAttribute("cityToSet", cityToSet);
        }
        else {
            List<Order> orderList = (List<Order>)  request.getSession().getAttribute("orders");
            Set<String> cityFromSet = OrderDao.cityFromSet(orderList);
            Set<String> cityToSet = OrderDao.cityToSet(orderList);
            request.getSession().setAttribute("cityFromSet", cityFromSet);
            request.getSession().setAttribute("cityToSet", cityToSet);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

