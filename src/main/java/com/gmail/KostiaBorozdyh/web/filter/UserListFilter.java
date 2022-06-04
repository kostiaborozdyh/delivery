package com.gmail.KostiaBorozdyh.web.filter;

import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;

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
        if (request.getSession().getAttribute("pageNumberUser") == null) {
            List<Integer> list = Calculate.getPaginationList(userList);
            if (list == null) {
                request.getSession().setAttribute("shortUsers", userList);
            } else {
                request.getSession().setAttribute("shortUsers", Calculate.getFiveElements(userList, 1));
            }
            request.getSession().setAttribute("listNumberUser", list);
            request.getSession().setAttribute("pageNumberUser", 1);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
