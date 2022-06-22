package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.OrderDTO;
import com.gmail.KostiaBorozdyh.model.entity.User;
import com.gmail.KostiaBorozdyh.model.service.OrderService;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Convert;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class CreateOrderServletTest {
    @Test
    public void createOrderTest() throws  IOException {

        final CreateOrderServlet servlet = new CreateOrderServlet();
        MockedStatic<UserService> mockedSettings;
        MockedStatic<OrderService> mockedSettings2;
        MockedStatic<Convert> mockedSettings3;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final User user = mock(User.class);
        final OrderDTO order = mock(OrderDTO.class);
        mockedSettings2 =  mockStatic(OrderService.class);
        mockedSettings =  mockStatic(UserService.class);
        mockedSettings3 = mockStatic(Convert.class);
        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("newOrder")).thenReturn(order);
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(request,times(1)).getSession();
        verify(session,times(1)).removeAttribute("newOrder");
        verify(session,times(1)).removeAttribute("btn");
        verify(response).sendRedirect("/user/order.jsp");
        mockedSettings.close();
        mockedSettings2.close();
        mockedSettings3.close();
    }
}
