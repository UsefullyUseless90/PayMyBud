package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Commissions_pmb;
import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
import com.paymybuddy.paymybuddy.models.dao.EmbeddedTransaction;
import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.repositories.CommissionsRepository;
import com.paymybuddy.paymybuddy.repositories.TransactionRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {
    @MockBean
    private CommissionsRepository commissionsRepository;

    @MockBean
    private EmbeddedTransaction embeddedTransaction;

    @MockBean
    private TransactionDAO transactionDAO;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private UtilisateurDAO utilisateurDAO;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link TransactionService#getTransactions()}
     */
    @Test
    void testGetTransactions() {
        when(this.transactionRepository.findAll()).thenReturn((Iterable<TransactionDAO>) mock(Iterable.class));
        this.transactionService.getTransactions();
        verify(this.transactionRepository).findAll();
    }

    /**
     * Method under test: {@link TransactionService#creationTransaction(TransactionDTO, Integer)}
     */
    @Test
    void testCreationTransaction() throws Exception {
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

        EmbeddedTransaction embeddedTransaction = new EmbeddedTransaction();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        embeddedTransaction.setDate(fromResult);
        embeddedTransaction.setDestinataire(utilisateurDAO1);
        embeddedTransaction.setEmetteur(utilisateurDAO2);

        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.setDescription("The characteristics of someone or something");
        transactionDAO.setEmbeddedTransaction(embeddedTransaction);
        transactionDAO.setMontant(1);
        when(this.transactionRepository.save((TransactionDAO) any())).thenReturn(transactionDAO);

        Commissions_pmb commissions_pmb = new Commissions_pmb();
        commissions_pmb.setMontant(10.0d);
        commissions_pmb.setNumeroTransaction(10);
        when(this.commissionsRepository.save((Commissions_pmb) any())).thenReturn(commissions_pmb);
        UtilisateurDTO utilisateurDTO = mock(UtilisateurDTO.class);
        when(utilisateurDTO.getIdUtilisateur()).thenReturn(1);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setMontant(1);
        transactionDTO.setDestinataire(utilisateurDTO);
        TransactionDTO actualCreationTransactionResult = this.transactionService.creationTransaction(transactionDTO, 1);
        assertSame(fromResult, actualCreationTransactionResult.getDate());
        assertEquals(1, actualCreationTransactionResult.getMontant().intValue());
        assertEquals("The characteristics of someone or something", actualCreationTransactionResult.getDescription());
        UtilisateurDTO destinataire = actualCreationTransactionResult.getDestinataire();
        assertFalse(destinataire.isActif());
        List<RoleUtilisateurs> roles = destinataire.getRoles();
        assertEquals(1, roles.size());
        assertEquals("Nom Prenom", destinataire.getNomPrenom());
        UtilisateurDTO emetteur = actualCreationTransactionResult.getEmetteur();
        assertFalse(emetteur.isActif());
        assertEquals("Mot De Passe", destinataire.getMotDePasse());
        assertEquals(1, destinataire.getIdUtilisateur().intValue());
        assertEquals(1, emetteur.getIdUtilisateur().intValue());
        List<RoleUtilisateurs> roles1 = emetteur.getRoles();
        assertEquals(roles, roles1);
        assertEquals(1, roles1.size());
        assertEquals("Nom Prenom", emetteur.getNomPrenom());
        assertEquals("Mot De Passe", emetteur.getMotDePasse());
        verify(this.utilisateurRepository, atLeast(1)).findById((Integer) any());
        verify(this.transactionRepository).save((TransactionDAO) any());
        verify(this.commissionsRepository).save((Commissions_pmb) any());
        verify(utilisateurDTO).getIdUtilisateur();
    }

    /**
     * Method under test: {@link TransactionService#creationTransaction(TransactionDTO, Integer)}
     */
    @Test
    void testCreationTransactionDifferentParameters() throws Exception {
        when(this.utilisateurDAO.getFondsDisponibles()).thenReturn(0.5d);
        doNothing().when(this.utilisateurDAO).setAdresseEmail((String) any());
        doNothing().when(this.utilisateurDAO).setConnexionDAOSet((java.util.Set<ConnexionDAO>) any());
        doNothing().when(this.utilisateurDAO).setDateDeNaissance((Date) any());
        doNothing().when(this.utilisateurDAO).setFondsDisponibles(anyDouble());
        doNothing().when(this.utilisateurDAO).setIdUtilisateur((Integer) any());
        doNothing().when(this.utilisateurDAO).setMotDePasse((String) any());
        doNothing().when(this.utilisateurDAO).setNom((String) any());
        doNothing().when(this.utilisateurDAO).setPrenom((String) any());
        doNothing().when(this.utilisateurDAO).setTransactionDAOS((java.util.List<TransactionDAO>) any());
        this.utilisateurDAO.setAdresseEmail("jane.doe@example.org");
        this.utilisateurDAO.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        this.utilisateurDAO.setDateDeNaissance(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        this.utilisateurDAO.setFondsDisponibles(10.0d);
        this.utilisateurDAO.setIdUtilisateur(1);
        this.utilisateurDAO.setMotDePasse("Mot De Passe");
        this.utilisateurDAO.setNom("Nom");
        this.utilisateurDAO.setPrenom("Prenom");
        this.utilisateurDAO.setTransactionDAOS(new ArrayList<>());
        Optional<UtilisateurDAO> ofResult = Optional.of(this.utilisateurDAO);
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(ofResult);

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurDAO.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO.setDateDeNaissance(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO.setFondsDisponibles(10.0d);
        utilisateurDAO.setIdUtilisateur(1);
        utilisateurDAO.setMotDePasse("Mot De Passe");
        utilisateurDAO.setNom("Nom");
        utilisateurDAO.setPrenom("Prenom");
        utilisateurDAO.setTransactionDAOS(new ArrayList<>());

        UtilisateurDAO utilisateurDAO1 = new UtilisateurDAO();
        utilisateurDAO1.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO1.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO1.setDateDeNaissance(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO1.setFondsDisponibles(10.0d);
        utilisateurDAO1.setIdUtilisateur(1);
        utilisateurDAO1.setMotDePasse("Mot De Passe");
        utilisateurDAO1.setNom("Nom");
        utilisateurDAO1.setPrenom("Prenom");
        utilisateurDAO1.setTransactionDAOS(new ArrayList<>());

        EmbeddedTransaction embeddedTransaction = new EmbeddedTransaction();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        embeddedTransaction.setDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        embeddedTransaction.setDestinataire(utilisateurDAO);
        embeddedTransaction.setEmetteur(utilisateurDAO1);

        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.setDescription("The characteristics of someone or something");
        transactionDAO.setEmbeddedTransaction(embeddedTransaction);
        transactionDAO.setMontant(1);
        when(this.transactionRepository.save((TransactionDAO) any())).thenReturn(transactionDAO);

        Commissions_pmb commissions_pmb = new Commissions_pmb();
        commissions_pmb.setMontant(10.0d);
        commissions_pmb.setNumeroTransaction(10);
        when(this.commissionsRepository.save((Commissions_pmb) any())).thenReturn(commissions_pmb);
        UtilisateurDTO utilisateurDTO = mock(UtilisateurDTO.class);
        when(utilisateurDTO.getIdUtilisateur()).thenReturn(1);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setMontant(1);
        transactionDTO.setDestinataire(utilisateurDTO);
        assertThrows(Exception.class, () -> this.transactionService.creationTransaction(transactionDTO, 1));
        verify(this.utilisateurRepository, atLeast(1)).findById((Integer) any());
        verify(this.utilisateurDAO).getFondsDisponibles();
        verify(this.utilisateurDAO).setAdresseEmail((String) any());
        verify(this.utilisateurDAO).setConnexionDAOSet((java.util.Set<ConnexionDAO>) any());
        verify(this.utilisateurDAO).setDateDeNaissance((Date) any());
        verify(this.utilisateurDAO).setFondsDisponibles(anyDouble());
        verify(this.utilisateurDAO).setIdUtilisateur((Integer) any());
        verify(this.utilisateurDAO).setMotDePasse((String) any());
        verify(this.utilisateurDAO).setNom((String) any());
        verify(this.utilisateurDAO).setPrenom((String) any());
        verify(this.utilisateurDAO).setTransactionDAOS((java.util.List<TransactionDAO>) any());
        verify(utilisateurDTO).getIdUtilisateur();
    }

    /**
     * Method under test: {@link TransactionService#getTransactionsById(Integer)}
     */
    @Test
    void testGetTransactionsById() {
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
        ArrayList<TransactionDAO> transactionDAOList = new ArrayList<>();
        when(this.transactionRepository.getMyTransactions((UtilisateurDAO) any())).thenReturn(transactionDAOList);
        List<TransactionDAO> actualTransactionsById = this.transactionService.getTransactionsById(1);
        assertSame(transactionDAOList, actualTransactionsById);
        assertTrue(actualTransactionsById.isEmpty());
        verify(this.utilisateurRepository).findById((Integer) any());
        verify(this.transactionRepository).getMyTransactions((UtilisateurDAO) any());
    }


    @Test
    void testGetTransactionsByIdRepo() {
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(Optional.empty());
        when(this.transactionRepository.getMyTransactions((UtilisateurDAO) any())).thenReturn(new ArrayList<>());
        assertTrue(this.transactionService.getTransactionsById(1).isEmpty());
        verify(this.utilisateurRepository).findById((Integer) any());
    }
}

