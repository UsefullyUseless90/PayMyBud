package com.paymybuddy.paymybuddy.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.paymybuddy.models.dao.EmbeddedTransaction;
import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.services.ITransactionService;

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

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @MockBean
    private ITransactionService iTransactionService;

    @Autowired
    private TransactionController transactionController;

    /**
     * Method under test: {@link TransactionController#getTransactions()}
     */
    @Test
    void testGetTransactions() throws Exception {
        when(this.iTransactionService.getTransactions()).thenReturn((Iterable<TransactionDAO>) mock(Iterable.class));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link TransactionController#getTransactions()}
     */
    @Test
    void testGetTransactions2() throws Exception {
        when(this.iTransactionService.getTransactions()).thenReturn((Iterable<TransactionDAO>) mock(Iterable.class));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/transactions");
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link TransactionController#creationTransaction(TransactionDTO, Integer)}
     */
    @Test
    void testCreationTransaction() throws Exception {
        when(this.iTransactionService.creationTransaction((TransactionDTO) any(), (Integer) any()))
                .thenReturn(new TransactionDTO());

        TransactionDTO transactionDTO = new TransactionDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        transactionDTO.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        transactionDTO.setDescription("The characteristics of someone or something");
        transactionDTO.setDestinataire(new UtilisateurDTO());
        transactionDTO.setEmetteur(new UtilisateurDTO());
        transactionDTO.setMontant(1);
        String content = (new ObjectMapper()).writeValueAsString(transactionDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/transactions/nouvelleTransaction/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"emetteur\":{\"idUtilisateur\":null,\"adresseEmail\":null,\"nomPrenom\":null,\"motDePasse\":null,\"actif\":false"
                                        + ",\"roles\":null},\"destinataire\":{\"idUtilisateur\":null,\"adresseEmail\":null,\"nomPrenom\":null,\"motDePasse"
                                        + "\":null,\"actif\":false,\"roles\":null},\"description\":\"The characteristics of someone or something\",\"date"
                                        + "\":0,\"montant\":1}"));
    }

    /**
     * Method under test: {@link TransactionController#getTransactionParDestinataire(Integer)}
     */
    @Test
    void testGetTransactionParDestinataire() throws Exception {
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

        EmbeddedTransaction embeddedTransaction = new EmbeddedTransaction();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        embeddedTransaction.setDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        embeddedTransaction.setDestinataire(utilisateurDAO);
        embeddedTransaction.setEmetteur(utilisateurDAO1);

        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.setDescription("The characteristics of someone or something");
        transactionDAO.setEmbeddedTransaction(embeddedTransaction);
        transactionDAO.setMontant(1);
        Optional<TransactionDAO> ofResult = Optional.of(transactionDAO);
        when(this.iTransactionService.getEchangeParDestinataire((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/transactions/destinataire/{id}",
                1);
        MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"embeddedTransaction\":{\"date\":0,\"emetteur\":{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail"
                                        + "\":\"jane.doe@example.org\",\"dateDeNaissance\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"},"
                                        + "\"destinataire\":{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail\":\"jane.doe@example.org"
                                        + "\",\"dateDeNaissance\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"}},\"description\":\"The"
                                        + " characteristics of someone or something\",\"montant\":1}"));
    }

    /**
     * Method under test: {@link TransactionController#getTransactionParDestinataire(Integer)}
     */
    @Test
    void testGetTransactionParDestinataire2() throws Exception {
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

        EmbeddedTransaction embeddedTransaction = new EmbeddedTransaction();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        embeddedTransaction.setDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        embeddedTransaction.setDestinataire(utilisateurDAO);
        embeddedTransaction.setEmetteur(utilisateurDAO1);

        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.setDescription("The characteristics of someone or something");
        transactionDAO.setEmbeddedTransaction(embeddedTransaction);
        transactionDAO.setMontant(1);
        Optional<TransactionDAO> ofResult = Optional.of(transactionDAO);
        when(this.iTransactionService.getEchangeParDestinataire((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/transactions/destinataire/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"embeddedTransaction\":{\"date\":0,\"emetteur\":{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail"
                                        + "\":\"jane.doe@example.org\",\"dateDeNaissance\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"},"
                                        + "\"destinataire\":{\"idUtilisateur\":1,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"adresseEmail\":\"jane.doe@example.org"
                                        + "\",\"dateDeNaissance\":0,\"fondsDisponibles\":10.0,\"motDePasse\":\"Mot De Passe\"}},\"description\":\"The"
                                        + " characteristics of someone or something\",\"montant\":1}"));
    }

    /**
     * Method under test: {@link TransactionController#getTransactionParId(Integer)}
     */
    @Test
    void testGetTransactionParId() throws Exception {
        when(this.iTransactionService.getTransactionsById((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/transactions/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TransactionController#getTransactionParId(Integer)}
     */
    @Test
    void testGetTransactionParId2() throws Exception {
        when(this.iTransactionService.getTransactionsById((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/transactions/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TransactionController#getTransactionParId(Integer)}
     */
    @Test
    void testGetTransactionParId3() throws Exception {
        when(this.iTransactionService.getTransactions()).thenReturn((Iterable<TransactionDAO>) mock(Iterable.class));
        when(this.iTransactionService.getTransactionsById((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/transactions/{id}", "", "Uri Vars");
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.transactionController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }
}

