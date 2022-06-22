package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
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

public class PayServletTest {
    @Test
    public void payTest() throws IOException, ServletException {

        final PayServlet servlet = new PayServlet();
        MockedStatic<UserService> mockedSettings;
        MockedStatic<OrderService> mockedSettings2;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final User user = mock(User.class);
        mockedSettings2 = mockStatic(OrderService.class);
        mockedSettings = mockStatic(UserService.class);
        when(request.getParameter("id")).thenReturn("1000");
        when(request.getParameter("value")).thenReturn("1000");
        when(request.getParameter("money")).thenReturn("1000");
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(2)).getSession();
        verify(session,times(1)).setAttribute("money",user.getMoney());
        verify(response).sendRedirect("/resetOrder");
        mockedSettings.close();
        mockedSettings2.close();
    }
}
