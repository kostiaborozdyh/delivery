package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.ReviewService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Controller for Adding Review
 */
@WebServlet(name = "AddReviewServlet", value = "/addReview")
public class AddReviewServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userResponse = request.getParameter("response");
        User user = (User) request.getSession().getAttribute("user");
        ReviewService.save(user, userResponse);
        response.sendRedirect("/reviews.jsp");
    }
}
