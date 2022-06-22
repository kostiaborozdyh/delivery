package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class SendEmailOrderServletTest {
    @Test
    public void sendEmailOrderTest() throws IOException, ServletException {

        final SendEmailOrderServlet servlet = new SendEmailOrderServlet();
        MockedStatic<UserService> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        mockedSettings = mockStatic(UserService.class);
        when(request.getParameter("idOrder")).thenReturn("1000");
        servlet.doGet(request, response);
        verify(request,times(0)).getSession();
        verify(response).sendRedirect("/user/order.jsp");
        mockedSettings.close();
    }
}
