package com.paymybuddy.paymybuddy.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.paymybuddy.config.JwtManager;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtManager jwtManager;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link AuthController#createAuthenticationToken(UtilisateurDTO)}
     */
    @Test
    void testCreateAuthenticationToken() throws Exception {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdUtilisateur(1);
        when(this.utilisateurRepository.findByAdresseEmail((String) any())).thenReturn(utilisateurDTO);
        when(this.userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        when(this.jwtManager.generateToken((Object) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(new BearerTokenAuthenticationToken("ABC123"));

        UtilisateurDTO utilisateurDTO1 = new UtilisateurDTO();
        utilisateurDTO1.setActif(true);
        utilisateurDTO1.setAdresseEmail("jane.doe@example.org");
        utilisateurDTO1.setIdUtilisateur(1);
        utilisateurDTO1.setMotDePasse("Mot De Passe");
        utilisateurDTO1.setNomPrenom("Nom Prenom");
        utilisateurDTO1.setRoles(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateurDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"idUtilisateur\":\"1\",\"token\":\"ABC123\"}"));
    }
}

