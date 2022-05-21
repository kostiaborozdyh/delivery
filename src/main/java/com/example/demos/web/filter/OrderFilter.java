package com.example.demos.web.filter;

import com.example.demos.model.OrderDao;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        if(request.getSession().getAttribute("orders")==null)
            request.getSession().setAttribute("orders", OrderDao.getUserOrders((User)request.getSession().getAttribute("user")));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

