package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;


public class BlockUserServletTest {
    @Test
    public void blockUserTest() throws ServletException, IOException {
         MockedStatic<UserService> mockedSettings;
        final BlockUserServlet servlet = new BlockUserServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        mockedSettings = mockStatic(UserService.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("100");
        servlet.doPost(request, response);
        verify(response).sendRedirect("/adm/usersTable.jsp");
        mockedSettings.close();
    }
}
