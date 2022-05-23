package com.example.demos.web.filter;

import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AcceptOrderFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getSession().getAttribute("city")==null) response.sendRedirect("/employee/ordersTable.jsp");
        else {
            List<Order> orderList = OrderDao.getOrderListOnRecord((String) request.getSession().getAttribute("city"));
            request.getSession().setAttribute("orderList",orderList);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
