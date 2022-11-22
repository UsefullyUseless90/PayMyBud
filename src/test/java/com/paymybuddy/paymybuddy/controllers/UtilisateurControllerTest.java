package com.paymybuddy.paymybuddy.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import com.paymybuddy.paymybuddy.services.IUtilisateurService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UtilisateurController.class, UtilisateurDAO.class})
@ExtendWith(SpringExtension.class)
class UtilisateurControllerTest {
    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @MockBean
    private IUtilisateurService iUtilisateurService;

    @Autowired
    private UtilisateurController utilisateurController;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link UtilisateurController#getUtilisateurParIdentifiant(Integer)}
     */
    @Test
    void testGetUtilisateurParIdentifiant() throws Exception {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurDAO.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO.setDateDeNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO.setFondsDisponibles(10.0d);
        utilisateurDAO.setIdUtilisateur(1);
        utilisateurDAO.setMotDePasse("Mot De Passe");
        utilisateurDAO.setNom("Nom");
        utilisateurDAO.setPrenom("Prenom");
        utilisateurDAO.setTransactionDAOS(new ArrayList<>());
        Optional<UtilisateurDAO> ofResult = Optional.of(utilisateurDAO);
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/utilisateur/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail\":\"jane.doe@example.org\",\"dateDeNaissance"
                                        + "\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"}"));
    }

    /**
     * Method under test: {@link UtilisateurController#saveUtilisateur(UtilisateurDAO)}
     */
    @Test
    void testSaveUtilisateur() throws Exception {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurDAO.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO.setDateDeNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO.setFondsDisponibles(10.0d);
        utilisateurDAO.setIdUtilisateur(1);
        utilisateurDAO.setMotDePasse("Mot De Passe");
        utilisateurDAO.setNom("Nom");
        utilisateurDAO.setPrenom("Prenom");
        utilisateurDAO.setTransactionDAOS(new ArrayList<>());
        when(this.iUtilisateurService.saveUtilisateur((UtilisateurDAO) any())).thenReturn(utilisateurDAO);

        UtilisateurDAO utilisateurDAO1 = new UtilisateurDAO();
        utilisateurDAO1.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO1.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO1.setDateDeNaissance(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO1.setFondsDisponibles(10.0d);
        utilisateurDAO1.setIdUtilisateur(1);
        utilisateurDAO1.setMotDePasse("Mot De Passe");
        utilisateurDAO1.setNom("Nom");
        utilisateurDAO1.setPrenom("Prenom");
        utilisateurDAO1.setTransactionDAOS(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateurDAO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail\":\"jane.doe@example.org\",\"dateDeNaissance"
                                        + "\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"}"));
    }

    /**
     * Method under test: {@link UtilisateurController#getUtilisateurParIdentifiant(Integer)}
     */
    @Test
    void testGetUtilisateurParIdentifiant2() throws Exception {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurDAO.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO.setDateDeNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO.setFondsDisponibles(10.0d);
        utilisateurDAO.setIdUtilisateur(1);
        utilisateurDAO.setMotDePasse("Mot De Passe");
        utilisateurDAO.setNom("Nom");
        utilisateurDAO.setPrenom("Prenom");
        utilisateurDAO.setTransactionDAOS(new ArrayList<>());
        Optional<UtilisateurDAO> ofResult = Optional.of(utilisateurDAO);
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/utilisateur/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail\":\"jane.doe@example.org\",\"dateDeNaissance"
                                        + "\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"}"));
    }

    /**
     * Method under test: {@link UtilisateurController#getUtilisateurs()}
     */
    @Test
    void testGetUtilisateurs() throws Exception {
        when(this.iUtilisateurService.getTousLesUtilisateurs()).thenReturn((Iterable<UtilisateurDAO>) mock(Iterable.class));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UtilisateurController#getUtilisateurs()}
     */
    @Test
    void testGetUtilisateurs2() throws Exception {
        when(this.iUtilisateurService.getTousLesUtilisateurs()).thenReturn((Iterable<UtilisateurDAO>) mock(Iterable.class));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/utilisateurs");
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link UtilisateurController#messageDeBienvenue()}
     */
    @Test
    void testMessageDeBienvenue() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/accueil");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Bienvenue chez PayMyBuddy"));
    }

    /**
     * Method under test: {@link UtilisateurController#messageDeBienvenue()}
     */
    @Test
    void testMessageDeBienvenue2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/accueil");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Bienvenue chez PayMyBuddy"));
    }

    /**
     * Method under test: {@link UtilisateurController#updatePerson(UtilisateurDAO, Integer)}
     */
    @Test
    void testUpdatePerson() throws Exception {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurDAO.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO.setDateDeNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO.setFondsDisponibles(10.0d);
        utilisateurDAO.setIdUtilisateur(1);
        utilisateurDAO.setMotDePasse("Mot De Passe");
        utilisateurDAO.setNom("Nom");
        utilisateurDAO.setPrenom("Prenom");
        utilisateurDAO.setTransactionDAOS(new ArrayList<>());
        Optional<UtilisateurDAO> ofResult = Optional.of(utilisateurDAO);

        UtilisateurDAO utilisateurDAO1 = new UtilisateurDAO();
        utilisateurDAO1.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO1.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO1.setDateDeNaissance(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO1.setFondsDisponibles(10.0d);
        utilisateurDAO1.setIdUtilisateur(1);
        utilisateurDAO1.setMotDePasse("Mot De Passe");
        utilisateurDAO1.setNom("Nom");
        utilisateurDAO1.setPrenom("Prenom");
        utilisateurDAO1.setTransactionDAOS(new ArrayList<>());
        when(this.utilisateurRepository.save((UtilisateurDAO) any())).thenReturn(utilisateurDAO1);
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(ofResult);

        UtilisateurDAO utilisateurDAO2 = new UtilisateurDAO();
        utilisateurDAO2.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO2.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO2.setDateDeNaissance(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO2.setFondsDisponibles(10.0d);
        utilisateurDAO2.setIdUtilisateur(1);
        utilisateurDAO2.setMotDePasse("Mot De Passe");
        utilisateurDAO2.setNom("Nom");
        utilisateurDAO2.setPrenom("Prenom");
        utilisateurDAO2.setTransactionDAOS(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateurDAO2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/edit/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail\":\"jane.doe@example.org\",\"dateDeNaissance"
                                        + "\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"}"));
    }

    /**
     * Method under test: {@link UtilisateurController#updatePerson(UtilisateurDAO, Integer)}
     */
    @Test
    void testUpdatePerson2() throws Exception {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurDAO.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO.setDateDeNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO.setFondsDisponibles(10.0d);
        utilisateurDAO.setIdUtilisateur(1);
        utilisateurDAO.setMotDePasse("Mot De Passe");
        utilisateurDAO.setNom("Nom");
        utilisateurDAO.setPrenom("Prenom");
        utilisateurDAO.setTransactionDAOS(new ArrayList<>());
        when(this.utilisateurRepository.save((UtilisateurDAO) any())).thenReturn(utilisateurDAO);
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(Optional.empty());

        UtilisateurDAO utilisateurDAO1 = new UtilisateurDAO();
        utilisateurDAO1.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO1.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO1.setDateDeNaissance(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO1.setFondsDisponibles(10.0d);
        utilisateurDAO1.setIdUtilisateur(1);
        utilisateurDAO1.setMotDePasse("Mot De Passe");
        utilisateurDAO1.setNom("Nom");
        utilisateurDAO1.setPrenom("Prenom");
        utilisateurDAO1.setTransactionDAOS(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateurDAO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/edit/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }
}

