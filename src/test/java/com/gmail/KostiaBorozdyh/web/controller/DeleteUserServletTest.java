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

public class DeleteUserServletTest {
    @Test
    public void deleteUserTest() throws IOException, ServletException {

        final DeleteUserServlet servlet = new DeleteUserServlet();
        MockedStatic<UserService> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        mockedSettings = mockStatic(UserService.class);
        when(request.getParameter("id")).thenReturn("1000");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(session, times(1)).removeAttribute("pageNumberUser");
        verify(response).sendRedirect("/adm/usersTable.jsp");
        mockedSettings.close();
    }
}
