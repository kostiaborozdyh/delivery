package com.gmail.KostiaBorozdyh.web.controller;


import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ResetOrderServletTest {
    @Test
    public void resetOrderForUserTest() throws IOException, ServletException {

        final ResetOrderServlet servlet = new ResetOrderServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(session, times(1)).removeAttribute("filter");
        verify(session, times(1)).removeAttribute("pageNumberOrder");
        verify(session, times(1)).removeAttribute("orders");
        verify(response).sendRedirect("/user/order.jsp");
    }

    @Test
    public void resetOrderForManagerTest() throws IOException, ServletException {

        final ResetOrderServlet servlet = new ResetOrderServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("manager");
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(session, times(1)).removeAttribute("filter");
        verify(session, times(1)).removeAttribute("pageNumberOrder");
        verify(session, times(1)).removeAttribute("orders");
        verify(response).sendRedirect("/man/orderList.jsp");
    }
}
