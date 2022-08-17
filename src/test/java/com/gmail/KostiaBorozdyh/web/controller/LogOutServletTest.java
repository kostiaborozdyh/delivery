package com.gmail.KostiaBorozdyh.web.controller;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogOutServletTest {
    @Test
    public void logOutTest() throws IOException, ServletException {

        final LogOutServlet servlet = new LogOutServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(session, times(1)).invalidate();
        verify(response).sendRedirect("/index.jsp");
    }
}
