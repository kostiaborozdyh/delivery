package com.gmail.KostiaBorozdyh.web.controller;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ChangeCityServletTest {
    @Test
    public void changeCityTest() throws ServletException, IOException {

        final ChangeCityServlet servlet = new ChangeCityServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(1)).getSession();
        verify(response).sendRedirect("/employee/ordersTable.jsp");
    }
}
