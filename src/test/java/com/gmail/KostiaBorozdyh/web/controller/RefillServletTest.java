package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class RefillServletTest {
    @Test
    public void refillTest() throws IOException, ServletException {

        final RefillServlet servlet = new RefillServlet();
        MockedStatic<UserService> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final User user = mock(User.class);
        mockedSettings = mockStatic(UserService.class);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("value")).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request, times(2)).getSession();
        verify(session, times(1)).setAttribute("money", user.getMoney());
        verify(response).sendRedirect("/user/refill.jsp");
        mockedSettings.close();
    }
}
