package com.paymybuddy.paymybuddy.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfig.class, SecurityConfig.WebConfiguration.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigTest {
    @Autowired
    private SecurityConfig.WebConfiguration webConfiguration;

    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link SecurityConfig#securityFilterChain(HttpSecurity)}
     */
    @Test
    void testSecurityFilterChain() throws Exception {
        assertTrue(this.securityConfig
                .securityFilterChain(null) instanceof org.springframework.security.web.DefaultSecurityFilterChain);
    }

    /**
     * Method under test: {@link WebConfiguration#appAuthenticationSuccessHandler()}
     */
    @Test
    void testWebConfigurationAppAuthenticationSuccessHandler() {
        assertTrue(this.webConfiguration
                .appAuthenticationSuccessHandler() instanceof SecurityConfig.WebConfiguration.AppAuthenticationSuccessHandler);
    }

    /**
     * Method under test: {@link WebConfiguration#authenticationProvider()}
     */
    @Test
    void testWebConfigurationAuthenticationProvider() {

        DaoAuthenticationProvider actualAuthenticationProviderResult = ((new SecurityConfig()).new WebConfiguration())
                .authenticationProvider();
        assertTrue(actualAuthenticationProviderResult
                .getUserCache() instanceof org.springframework.security.core.userdetails.cache.NullUserCache);
        assertTrue(actualAuthenticationProviderResult.isHideUserNotFoundExceptions());
        assertFalse(actualAuthenticationProviderResult.isForcePrincipalAsString());
    }

    /**
     * Method under test: {@link WebConfiguration#corsConfigurationSource()}
     */
    @Test
    void testWebConfigurationCorsConfigurationSource() {
        assertTrue(this.webConfiguration
                .corsConfigurationSource() instanceof org.springframework.web.cors.UrlBasedCorsConfigurationSource);
    }

    /**
     * Method under test: {@link WebConfiguration#passwordEncoder()}
     */
    @Test
    void testWebConfigurationPasswordEncoder() {
        assertTrue(this.webConfiguration
                .passwordEncoder() instanceof org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder);
    }

    /**
     * Method under test: {@link WebConfiguration#userDetailsService()}
     */
    @Test
    void testWebConfigurationUserDetailsService() {
        assertTrue(this.webConfiguration.userDetailsService() instanceof CustomUserDetailsService);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AppAuthenticationSuccessHandler#AppAuthenticationSuccessHandler(SecurityConfig.WebConfiguration)}
     *   <li>{@link AppAuthenticationSuccessHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)}
     * </ul>
     */
    @Test
    void testWebConfiguration_AppAuthenticationSuccessHandlerConstructor() throws IOException, ServletException {

        SecurityConfig.WebConfiguration.AppAuthenticationSuccessHandler actualAppAuthenticationSuccessHandler = ((new SecurityConfig()).new WebConfiguration()).new AppAuthenticationSuccessHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        actualAppAuthenticationSuccessHandler.handle(request, response, new BearerTokenAuthenticationToken("ABC123"));
    }
}

