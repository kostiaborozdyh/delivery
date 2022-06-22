package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class InfoOrderServletTest {
    @Test
    public void infoOrderUserTest() throws IOException, ServletException {

        final InfoOrderServlet servlet = new InfoOrderServlet();
        MockedStatic<OrderService> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        mockedSettings = mockStatic(OrderService.class);
        when(request.getParameter("id")).thenReturn("1000");
        when(session.getAttribute("role")).thenReturn("user");
        when(request.getSession()).thenReturn(session);
        servlet.doGet(request, response);
        verify(request,times(2)).getSession();
        verify(response).sendRedirect("/user/userOrder.jsp");
        mockedSettings.close();
    }
    @Test
    public void infoOrderManagerTest() throws IOException, ServletException {

        final InfoOrderServlet servlet = new InfoOrderServlet();
        MockedStatic<OrderService> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        mockedSettings = mockStatic(OrderService.class);
        when(request.getParameter("id")).thenReturn("1000");
        when(session.getAttribute("role")).thenReturn("manager");
        when(request.getSession()).thenReturn(session);
        servlet.doGet(request, response);
        verify(request,times(2)).getSession();
        verify(response).sendRedirect("/man/orderUser.jsp");
        mockedSettings.close();
    }
}
