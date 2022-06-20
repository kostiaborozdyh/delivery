package com.gmail.KostiaBorozdyh.web.filter;

import com.gmail.KostiaBorozdyh.model.entity.Review;
import com.gmail.KostiaBorozdyh.model.service.ReviewService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ReviewsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        List<Review> reviewList = ReviewService.getAllReviews();
        session.setAttribute("reviews", reviewList);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
