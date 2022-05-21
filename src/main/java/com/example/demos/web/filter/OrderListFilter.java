package com.example.demos.web.filter;

import com.example.demos.model.OrderDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        if(request.getSession().getAttribute("orderList")==null)
            request.getSession().setAttribute("orderList", OrderDao.getOrderList());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
