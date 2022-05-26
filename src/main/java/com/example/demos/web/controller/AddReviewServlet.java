package com.example.demos.web.controller;

import com.example.demos.model.ReviewDao;
import com.example.demos.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddReviewServlet", value = "/addReview")
public class AddReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userResponse = request.getParameter("response");
        ReviewDao.addReview(((User) request.getSession().getAttribute("user")).getId(),userResponse);
        response.sendRedirect("/reviews.jsp");
    }
}
