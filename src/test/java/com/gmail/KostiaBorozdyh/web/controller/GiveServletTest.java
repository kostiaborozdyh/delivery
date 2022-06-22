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

public class GiveServletTest {
    @Test
    public void giveCodEqualsTest() throws IOException, ServletException {

        final GiveServlet servlet = new GiveServlet();
        MockedStatic<OrderService> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        mockedSettings = mockStatic(OrderService.class);
        when(session.getAttribute("id")).thenReturn(1000);
        when(request.getParameter("code")).thenReturn("123456");
        when(session.getAttribute("code")).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(2)).getSession();
        verify(response).sendRedirect("/chooseCity");
        mockedSettings.close();
    }
    @Test
    public void giveCodNotEqualsTest() throws IOException, ServletException {

        final GiveServlet servlet = new GiveServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("id")).thenReturn(1000);
        when(request.getParameter("code")).thenReturn("123476");
        when(session.getAttribute("code")).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(3)).getSession();
        verify(session,times(1)).setAttribute("invalidCode","invalidCode");
        verify(response).sendRedirect("/employee/enterCode.jsp");
    }
}
