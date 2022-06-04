package com.gmail.KostiaBorozdyh.web.filter;

import com.gmail.KostiaBorozdyh.model.dao.UserDao;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        List<User> userList;
        List<Integer>list;
        Integer count;
        if (session.getAttribute("pageNumberUser") == null) {
            count=UserDao.getUserCount();
            userList = UserDao.getUsers(0);
             list = Calculate.getPaginationList(count);
            session.setAttribute("listNumberUser", list);
            session.setAttribute("pageNumberUser", 1);
            session.setAttribute("userList", userList);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
