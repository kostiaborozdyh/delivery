package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ConfirmOrderServletTest {
    @Test
    public void confirmOrderTest() throws ServletException, IOException {

        final ConfirmOrderServlet servlet = new ConfirmOrderServlet();
        MockedStatic<UserService> mockedSettings;
        MockedStatic<OrderService> mockedSettings2;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        mockedSettings2 =  mockStatic(OrderService.class);
        mockedSettings = mockStatic(UserService.class);
        when(request.getRequestDispatcher("/info")).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn("1000");
        when(request.getSession()).thenReturn(session);
        servlet.doGet(request, response);
        verify(request,times(1)).getSession();
        verify(request,times(1)).setAttribute("id", 1000);
        verify(dispatcher).forward(request, response);
        mockedSettings.close();
        mockedSettings2.close();
    }
}
