package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.security.Security;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class GiveOrderServletTest {
    @Test
    public void giveOrderTest() throws IOException, ServletException {

        final GiveOrderServlet servlet = new GiveOrderServlet();
        MockedStatic<UserService> mockedSettings;
        MockedStatic<Security> mockedSettings2;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        mockedSettings =  mockStatic(UserService.class);
        mockedSettings2 = mockStatic(Security.class);
        when(request.getParameter("id")).thenReturn("1000");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(2)).getSession();
        verify(response).sendRedirect("/employee/enterCode.jsp");
        mockedSettings.close();
        mockedSettings2.close();
    }
}
