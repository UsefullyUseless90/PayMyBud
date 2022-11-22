package com.paymybuddy.paymybuddy.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

class SecurityInterceptorFilterTest {

    /**
     * Method under test: {@link SecurityInterceptorFilter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object)}
     */
    @Test
    void testPreHandle() throws Exception {
        SecurityInterceptorFilter securityInterceptorFilter = new SecurityInterceptorFilter();
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getRequestURI()).thenReturn("public");
        when(defaultMultipartHttpServletRequest.getMethod()).thenReturn("https://example.org/example");
        when(defaultMultipartHttpServletRequest.getSession()).thenReturn(new MockHttpSession());
        assertTrue(securityInterceptorFilter.preHandle(defaultMultipartHttpServletRequest, new Response(), "Handler"));
        verify(defaultMultipartHttpServletRequest).getMethod();
        verify(defaultMultipartHttpServletRequest).getRequestURI();
        verify(defaultMultipartHttpServletRequest).getSession();
    }

    /**
     * Method under test: {@link SecurityInterceptorFilter#saveUserInSession(UtilisateurDTO, javax.servlet.http.HttpServletRequest)}
     */
    @Test
    void testSaveUserInSession() {
        SecurityInterceptorFilter securityInterceptorFilter = new SecurityInterceptorFilter();
        UtilisateurDTO user = new UtilisateurDTO();
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        securityInterceptorFilter.saveUserInSession(user, mockHttpServletRequest);
        assertTrue(mockHttpServletRequest.getSession() instanceof org.springframework.mock.web.MockHttpSession);
    }
}

