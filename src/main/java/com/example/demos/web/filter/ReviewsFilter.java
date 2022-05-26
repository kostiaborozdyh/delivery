package com.example.demos.web.filter;

import com.example.demos.model.ReviewDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
