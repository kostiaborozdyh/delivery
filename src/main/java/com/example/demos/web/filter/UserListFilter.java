package com.example.demos.web.filter;

import com.example.demos.model.UserDao;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


public class UserListFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            List<User> userList = UserDao.getUsers();
            request.getSession().setAttribute("userList", userList);
            filterChain.doFilter(servletRequest, servletResponse);
    }
}
