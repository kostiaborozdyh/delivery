package com.example.demos.web.filter;

import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.Order;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class OrderListFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getSession().getAttribute("orderList")==null) {
            List<Order> orderList = OrderDao.getOrderList();
            Set<String> cityFromSet = OrderDao.cityFromSet(orderList);
            Set<String> cityToSet = OrderDao.cityToSet(orderList);
            request.getSession().setAttribute("orderList", orderList);
            request.getSession().setAttribute("cityFromSet", cityFromSet);
            request.getSession().setAttribute("cityToSet", cityToSet);
        }
        else {
            List<Order> orderList = (List<Order>)  request.getSession().getAttribute("orderList");
            Set<String> cityFromSet = OrderDao.cityFromSet(orderList);
            Set<String> cityToSet = OrderDao.cityToSet(orderList);
            request.getSession().setAttribute("cityFromSet", cityFromSet);
            request.getSession().setAttribute("cityToSet", cityToSet);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
