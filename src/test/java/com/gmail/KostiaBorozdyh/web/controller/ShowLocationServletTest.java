package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dto.PointDTO;
import com.gmail.KostiaBorozdyh.model.service.UserService;
import com.gmail.KostiaBorozdyh.model.utils.Calculate;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ShowLocationServletTest {
    @Test
    public void showLocationTest() throws IOException, ServletException {

        final ShowLocationServlet servlet = new ShowLocationServlet();
        MockedStatic<Calculate> mockedSettings;
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final PointDTO pointDTO = mock(PointDTO.class);
        mockedSettings = mockStatic(Calculate.class);
        when(request.getParameter("id")).thenReturn("1000");
        when(request.getSession()).thenReturn(session);
        when(Calculate.getPointAtTheMoment(1000)).thenReturn(pointDTO);
        servlet.doGet(request, response);
        verify(request, times(2)).getSession();
        verify(session, times(1)).setAttribute("latitude", pointDTO.getLatitude());
        verify(session, times(1)).setAttribute("longitude", pointDTO.getLongitude());
        verify(response).sendRedirect("/user/location.jsp");
        mockedSettings.close();
    }
}
