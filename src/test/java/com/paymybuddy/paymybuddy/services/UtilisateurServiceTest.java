package com.paymybuddy.paymybuddy.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UtilisateurService.class, UtilisateurDAO.class})
@ExtendWith(SpringExtension.class)
class UtilisateurServiceTest {
    @MockBean
    private UtilisateurDAO utilisateurDAO;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Method under test: {@link UtilisateurService#getTousLesUtilisateurs()}
     */
    @Test
    void testGetTousLesUtilisateurs() {
        when(this.utilisateurRepository.findAll()).thenReturn((Iterable<UtilisateurDAO>) mock(Iterable.class));
        this.utilisateurService.getTousLesUtilisateurs();
        verify(this.utilisateurRepository).findAll();
    }

    /**
     * Method under test: {@link UtilisateurService#getUtilisateurId()}
     */
    @Test
    void testGetUtilisateurId() {
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
        when(this.utilisateurDAO.getIdUtilisateur()).thenReturn(1);
        Optional<UtilisateurDAO> actualUtilisateurId = this.utilisateurService.getUtilisateurId();
        assertSame(ofResult, actualUtilisateurId);
        assertTrue(actualUtilisateurId.isPresent());
        verify(this.utilisateurRepository).findById((Integer) any());
        verify(this.utilisateurDAO).getIdUtilisateur();
    }

    /**
     * Method under test: {@link UtilisateurService#saveUtilisateur(UtilisateurDAO)}
     */
    @Test
    void testSaveUtilisateur() {
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

        UtilisateurDAO utilisateurDAO1 = new UtilisateurDAO();
        utilisateurDAO1.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO1.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        utilisateurDAO1.setDateDeNaissance(fromResult);
        utilisateurDAO1.setFondsDisponibles(10.0d);
        utilisateurDAO1.setIdUtilisateur(1);
        utilisateurDAO1.setMotDePasse("Mot De Passe");
        utilisateurDAO1.setNom("Nom");
        utilisateurDAO1.setPrenom("Prenom");
        utilisateurDAO1.setTransactionDAOS(new ArrayList<>());
        assertSame(utilisateurDAO, this.utilisateurService.saveUtilisateur(utilisateurDAO1));
        verify(this.utilisateurRepository).save((UtilisateurDAO) any());
        assertEquals("jane.doe@example.org", utilisateurDAO1.getAdresseEmail());
        assertEquals("Prenom", utilisateurDAO1.getPrenom());
        assertEquals("Nom", utilisateurDAO1.getNom());
        assertEquals("Mot De Passe", utilisateurDAO1.getMotDePasse());
        assertSame(fromResult, utilisateurDAO1.getDateDeNaissance());
    }
}

