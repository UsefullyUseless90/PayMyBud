package com.paymybuddy.paymybuddy.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.services.IConnexionService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ConnexionController.class})
@ExtendWith(SpringExtension.class)
class ConnexionControllerTest {
    @Autowired
    private ConnexionController connexionController;

    @MockBean
    private IConnexionService iConnexionService;

    /**
     * Method under test: {@link ConnexionController#creationConnexion(ConnexionDTO, Integer)}
     */
    @Test
    void testCreationConnexion() throws Exception {
        when(this.iConnexionService.creationConnexion((ConnexionDTO) any(), (Integer) any()))
                .thenReturn(new ConnexionDTO());

        ConnexionDTO connexionDTO = new ConnexionDTO();
        connexionDTO.setDemandeur(new UtilisateurDTO());
        connexionDTO.setReceveur(new UtilisateurDTO());
        String content = (new ObjectMapper()).writeValueAsString(connexionDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/connexions/nouvelleConnexion/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.connexionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"demandeur\":{\"idUtilisateur\":null,\"adresseEmail\":null,\"nomPrenom\":null,\"motDePasse\":null,\"actif\":false"
                                        + ",\"roles\":null},\"receveur\":{\"idUtilisateur\":null,\"adresseEmail\":null,\"nomPrenom\":null,\"motDePasse\":null"
                                        + ",\"actif\":false,\"roles\":null}}"));
    }

    /**
     * Method under test: {@link ConnexionController#getRelation(Integer)}
     */
    @Test
    void testGetRelation() throws Exception {
        when(this.iConnexionService.getConnexionById((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/connexions/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.connexionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ConnexionController#getRelation(Integer)}
     */
    @Test
    void testGetRelation2() throws Exception {
        when(this.iConnexionService.getConnexionById((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/connexions/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.connexionController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

