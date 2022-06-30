package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class AddReviewServletTest {


    @Test
    public void addReviewTest() throws ServletException, IOException {

        final AddReviewServlet servlet = new AddReviewServlet();
        MockedStatic<ReviewService> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final User user = mock(User.class);
        mockedSettings = mockStatic(ReviewService.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(user);
        servlet.doPost(request, response);
        verify(response).sendRedirect("/reviews.jsp");
        mockedSettings.close();
    }
}
