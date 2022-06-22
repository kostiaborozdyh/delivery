package com.gmail.KostiaBorozdyh.web.controller;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class RestorePasswordServletTest {
    @Test
    public void restorePasswordCodEqualsTest() throws IOException, ServletException {

        final RestorePasswordServlet servlet = new RestorePasswordServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getParameter("code")).thenReturn("123456");
        when(session.getAttribute("code")).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(1)).getSession();
        verify(response).sendRedirect("/restore/enterPassword.jsp");
    }
    @Test
    public void restorePasswordCodNotEqualsTest() throws IOException, ServletException {

        final RestorePasswordServlet servlet = new RestorePasswordServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getParameter("code")).thenReturn("123456");
        when(session.getAttribute("code")).thenReturn("123426");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(2)).getSession();
        verify(session,times(1)).setAttribute("invalidCode","invalidCode");
        verify(response).sendRedirect("/restore/restorePassword.jsp");
    }
}
