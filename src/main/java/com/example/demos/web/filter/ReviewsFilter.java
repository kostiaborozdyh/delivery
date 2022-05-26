package com.example.demos.web.filter;

import com.example.demos.model.entity.ReviewDao;
import com.example.demos.security.Security;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ReviewsFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig)  {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getSession().setAttribute("reviews", ReviewDao.getReviews());
        filterChain.doFilter(servletRequest, servletResponse);


    }
}
