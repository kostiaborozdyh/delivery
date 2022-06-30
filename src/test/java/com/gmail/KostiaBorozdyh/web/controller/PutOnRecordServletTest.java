package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class PutOnRecordServletTest {
    @Test
    public void putOnRecordTest() throws IOException, ServletException {

        final PutOnRecordServlet servlet = new PutOnRecordServlet();
        MockedStatic<UserService> mockedSettings;
        MockedStatic<OrderService> mockedSettings2;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        mockedSettings2 = mockStatic(OrderService.class);
        mockedSettings = mockStatic(UserService.class);
        when(request.getParameter("id")).thenReturn("1000");
        servlet.doPost(request, response);
        verify(request, times(0)).getSession();
        verify(response).sendRedirect("/employee/acceptOrder.jsp");
        mockedSettings.close();
        mockedSettings2.close();
    }
}
