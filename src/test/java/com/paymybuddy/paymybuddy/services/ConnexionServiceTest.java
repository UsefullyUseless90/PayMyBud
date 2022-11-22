package com.paymybuddy.paymybuddy.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
import com.paymybuddy.paymybuddy.models.dao.EmbeddedConnexion;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.repositories.ConnexionRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ConnexionService.class})
@ExtendWith(SpringExtension.class)
class ConnexionServiceTest {
    @MockBean
    private ConnexionRepository connexionRepository;

    @Autowired
    private ConnexionService connexionService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link ConnexionService#getConnexions()}
     */
    @Test
    void testGetConnexions() {
        when(this.connexionRepository.findAll()).thenReturn((Iterable<ConnexionDAO>) mock(Iterable.class));
        this.connexionService.getConnexions();
        verify(this.connexionRepository).findAll();
    }

    /**
     * Method under test: {@link ConnexionService#getConnexionById(Integer)}
     */
    @Test
    void testGetConnexionById() {
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
        assertTrue(this.connexionService.getConnexionById(1).isEmpty());
        verify(this.utilisateurRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ConnexionService#getConnexionById(Integer)}
     */
    @Test
    void testGetConnexionById2() {
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

        EmbeddedConnexion embeddedConnexion = new EmbeddedConnexion();
        embeddedConnexion.setDemandeur(utilisateurDAO);
        embeddedConnexion.setReceveur(utilisateurDAO1);

        ConnexionDAO connexionDAO = new ConnexionDAO();
        connexionDAO.setEmbeddedConnexion(embeddedConnexion);

        HashSet<ConnexionDAO> connexionDAOSet = new HashSet<>();
        connexionDAOSet.add(connexionDAO);

        UtilisateurDAO utilisateurDAO2 = new UtilisateurDAO();
        utilisateurDAO2.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO2.setConnexionDAOSet(connexionDAOSet);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO2.setDateDeNaissance(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO2.setFondsDisponibles(10.0d);
        utilisateurDAO2.setIdUtilisateur(1);
        utilisateurDAO2.setMotDePasse("Mot De Passe");
        utilisateurDAO2.setNom("Nom");
        utilisateurDAO2.setPrenom("Prenom");
        utilisateurDAO2.setTransactionDAOS(new ArrayList<>());
        Optional<UtilisateurDAO> ofResult = Optional.of(utilisateurDAO2);
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(ofResult);
        assertEquals(1, this.connexionService.getConnexionById(1).size());
        verify(this.utilisateurRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ConnexionService#getConnexionById(Integer)}
     */
    @Test
    void testGetConnexionById4() {
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

        EmbeddedConnexion embeddedConnexion = new EmbeddedConnexion();
        embeddedConnexion.setDemandeur(utilisateurDAO);
        embeddedConnexion.setReceveur(utilisateurDAO1);

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

        UtilisateurDAO utilisateurDAO3 = new UtilisateurDAO();
        utilisateurDAO3.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO3.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO3.setDateDeNaissance(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO3.setFondsDisponibles(10.0d);
        utilisateurDAO3.setIdUtilisateur(1);
        utilisateurDAO3.setMotDePasse("Mot De Passe");
        utilisateurDAO3.setNom("Nom");
        utilisateurDAO3.setPrenom("Prenom");
        utilisateurDAO3.setTransactionDAOS(new ArrayList<>());

        EmbeddedConnexion embeddedConnexion1 = new EmbeddedConnexion();
        embeddedConnexion1.setDemandeur(utilisateurDAO2);
        embeddedConnexion1.setReceveur(utilisateurDAO3);
        ConnexionDAO connexionDAO = mock(ConnexionDAO.class);
        when(connexionDAO.getEmbeddedConnexion()).thenReturn(embeddedConnexion1);
        doNothing().when(connexionDAO).setEmbeddedConnexion((EmbeddedConnexion) any());
        connexionDAO.setEmbeddedConnexion(embeddedConnexion);

        HashSet<ConnexionDAO> connexionDAOSet = new HashSet<>();
        connexionDAOSet.add(connexionDAO);

        UtilisateurDAO utilisateurDAO4 = new UtilisateurDAO();
        utilisateurDAO4.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO4.setConnexionDAOSet(connexionDAOSet);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO4.setDateDeNaissance(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO4.setFondsDisponibles(10.0d);
        utilisateurDAO4.setIdUtilisateur(1);
        utilisateurDAO4.setMotDePasse("Mot De Passe");
        utilisateurDAO4.setNom("Nom");
        utilisateurDAO4.setPrenom("Prenom");
        utilisateurDAO4.setTransactionDAOS(new ArrayList<>());
        Optional<UtilisateurDAO> ofResult = Optional.of(utilisateurDAO4);
        when(this.utilisateurRepository.findById((Integer) any())).thenReturn(ofResult);
        assertEquals(1, this.connexionService.getConnexionById(1).size());
        verify(this.utilisateurRepository).findById((Integer) any());
        verify(connexionDAO).getEmbeddedConnexion();
        verify(connexionDAO).setEmbeddedConnexion((EmbeddedConnexion) any());
    }


    /**
     * Method under test: {@link ConnexionService#creationConnexion(ConnexionDTO, Integer)}
     */
    @Test
    void testCreationConnexion2() {
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

        EmbeddedConnexion embeddedConnexion = new EmbeddedConnexion();
        embeddedConnexion.setDemandeur(utilisateurDAO1);
        embeddedConnexion.setReceveur(utilisateurDAO2);

        ConnexionDAO connexionDAO = new ConnexionDAO();
        connexionDAO.setEmbeddedConnexion(embeddedConnexion);
        when(this.connexionRepository.save((ConnexionDAO) any())).thenReturn(connexionDAO);

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdUtilisateur(1);

        ConnexionDTO connexionDTO = new ConnexionDTO();
        connexionDTO.setDemandeur(utilisateurDTO);
        ConnexionDTO actualCreationConnexionResult = this.connexionService.creationConnexion(connexionDTO, 1);
        UtilisateurDTO demandeur = actualCreationConnexionResult.getDemandeur();
        assertFalse(demandeur.isActif());
        UtilisateurDTO receveur = actualCreationConnexionResult.getReceveur();
        assertFalse(receveur.isActif());
        List<RoleUtilisateurs> roles = receveur.getRoles();
        assertEquals(1, roles.size());
        assertEquals("Nom Prenom", receveur.getNomPrenom());
        assertEquals("Mot De Passe", receveur.getMotDePasse());
        assertEquals("Mot De Passe", demandeur.getMotDePasse());
        List<RoleUtilisateurs> roles1 = demandeur.getRoles();
        assertEquals(roles, roles1);
        assertEquals(1, roles1.size());
        assertEquals("Nom Prenom", demandeur.getNomPrenom());
        assertEquals(1, receveur.getIdUtilisateur().intValue());
        assertEquals(1, demandeur.getIdUtilisateur().intValue());
        verify(this.utilisateurRepository, atLeast(1)).findById((Integer) any());
        verify(this.connexionRepository).save((ConnexionDAO) any());
    }

    /**
     * Method under test: {@link ConnexionService#creationConnexion(ConnexionDTO, Integer)}
     */
    @Test
    void testCreationConnexion3() {
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

        EmbeddedConnexion embeddedConnexion = new EmbeddedConnexion();
        embeddedConnexion.setDemandeur(utilisateurDAO1);
        embeddedConnexion.setReceveur(utilisateurDAO2);

        UtilisateurDAO utilisateurDAO3 = new UtilisateurDAO();
        utilisateurDAO3.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO3.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO3.setDateDeNaissance(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO3.setFondsDisponibles(10.0d);
        utilisateurDAO3.setIdUtilisateur(1);
        utilisateurDAO3.setMotDePasse("Mot De Passe");
        utilisateurDAO3.setNom("Nom");
        utilisateurDAO3.setPrenom("Prenom");
        utilisateurDAO3.setTransactionDAOS(new ArrayList<>());

        UtilisateurDAO utilisateurDAO4 = new UtilisateurDAO();
        utilisateurDAO4.setAdresseEmail("jane.doe@example.org");
        utilisateurDAO4.setConnexionDAOSet(new HashSet<>());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        utilisateurDAO4.setDateDeNaissance(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        utilisateurDAO4.setFondsDisponibles(10.0d);
        utilisateurDAO4.setIdUtilisateur(1);
        utilisateurDAO4.setMotDePasse("Mot De Passe");
        utilisateurDAO4.setNom("Nom");
        utilisateurDAO4.setPrenom("Prenom");
        utilisateurDAO4.setTransactionDAOS(new ArrayList<>());

        EmbeddedConnexion embeddedConnexion1 = new EmbeddedConnexion();
        embeddedConnexion1.setDemandeur(utilisateurDAO3);
        embeddedConnexion1.setReceveur(utilisateurDAO4);
        ConnexionDAO connexionDAO = mock(ConnexionDAO.class);
        when(connexionDAO.getEmbeddedConnexion()).thenReturn(embeddedConnexion1);
        doNothing().when(connexionDAO).setEmbeddedConnexion((EmbeddedConnexion) any());
        connexionDAO.setEmbeddedConnexion(embeddedConnexion);
        when(this.connexionRepository.save((ConnexionDAO) any())).thenReturn(connexionDAO);

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setIdUtilisateur(1);

        ConnexionDTO connexionDTO = new ConnexionDTO();
        connexionDTO.setDemandeur(utilisateurDTO);
        ConnexionDTO actualCreationConnexionResult = this.connexionService.creationConnexion(connexionDTO, 1);
        UtilisateurDTO demandeur = actualCreationConnexionResult.getDemandeur();
        assertFalse(demandeur.isActif());
        UtilisateurDTO receveur = actualCreationConnexionResult.getReceveur();
        assertFalse(receveur.isActif());
        List<RoleUtilisateurs> roles = receveur.getRoles();
        assertEquals(1, roles.size());
        assertEquals("Nom Prenom", receveur.getNomPrenom());
        assertEquals("Mot De Passe", receveur.getMotDePasse());
        assertEquals("Mot De Passe", demandeur.getMotDePasse());
        List<RoleUtilisateurs> roles1 = demandeur.getRoles();
        assertEquals(roles, roles1);
        assertEquals(1, roles1.size());
        assertEquals("Nom Prenom", demandeur.getNomPrenom());
        assertEquals(1, receveur.getIdUtilisateur().intValue());
        assertEquals(1, demandeur.getIdUtilisateur().intValue());
        verify(this.utilisateurRepository, atLeast(1)).findById((Integer) any());
        verify(this.connexionRepository).save((ConnexionDAO) any());
        verify(connexionDAO, atLeast(1)).getEmbeddedConnexion();
        verify(connexionDAO).setEmbeddedConnexion((EmbeddedConnexion) any());
    }
}

