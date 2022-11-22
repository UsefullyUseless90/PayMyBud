package com.paymybuddy.paymybuddy.ModelsTests;

import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
import com.paymybuddy.paymybuddy.models.dao.EmbeddedConnexion;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllConnexionsTests {

    EmbeddedConnexion eC = new EmbeddedConnexion();
    ConnexionDAO cDAO = new ConnexionDAO();
    ConnexionDTO cDTO = new ConnexionDTO();
    UtilisateurDAO uDAO = new UtilisateurDAO();
    UtilisateurDAO uDAO1 = new UtilisateurDAO();
    UtilisateurDTO uDTO = new UtilisateurDTO();
    UtilisateurDTO uDTO1 = new UtilisateurDTO();

    @BeforeEach
    void setUp(){
        //UtilisateurDAO//

        uDAO.setPrenom("Jacques");
        uDAO.setNom("Cartier");
        uDAO.setFondsDisponibles(1990);
        uDAO.setAdresseEmail("QCRules.com");
        Date naissance = new Date(1431, 9, 01);
        uDAO.setDateDeNaissance(naissance);
        uDAO.setIdUtilisateur(1431);
        uDAO.setMotDePasse("Quebec");

        //UtilisateurDAO1//

        uDAO1.setPrenom("Rene");
        uDAO1.setNom("Leveque");
        uDAO1.setFondsDisponibles(1990);
        uDAO1.setAdresseEmail("QCRules1.com");
        Date naissance1 = new Date(1922, 11, 25);
        uDAO1.setDateDeNaissance(naissance1);
        uDAO1.setIdUtilisateur(1922);
        uDAO1.setMotDePasse("PartiQuebec");

        //UtilisateurDTO//

        uDTO.setIdUtilisateur(789);
        uDTO.setAdresseEmail("SirPath.com");
        uDTO.setNomPrenom("Sir Path");
        uDTO.setMotDePasse("StLaurent");
        uDTO.setActif(true);
        uDTO.roles = new ArrayList<>();
        uDTO.roles.add(RoleUtilisateurs.USER);
        uDTO.setRoles(uDTO.roles);

        //UtilisateurDTO1//

        uDTO1.setIdUtilisateur(879);
        uDTO1.setAdresseEmail("PathSir.com");
        uDTO1.setNomPrenom("PathSir");
        uDTO1.setMotDePasse("StLaurent");
        uDTO1.setActif(true);
        uDTO1.roles = new ArrayList<>();
        uDTO1.roles.add(RoleUtilisateurs.USER);
        uDTO1.setRoles(uDTO.roles);

        //ConnexionDTO//

        cDTO.setDemandeur(uDTO);
        cDTO.setReceveur(uDTO1);

        //ConnexionDAO//
        cDAO.setEmbeddedConnexion(eC);

        //EmbeddedConnexion//
        eC.setReceveur(uDAO1);
        eC.setDemandeur(uDAO);
    }
    @Test
    void TestConstructorEC(){
        assertEquals(uDAO1, eC.getReceveur());
        assertEquals(uDAO, eC.getDemandeur());
    }
    @Test
    void TestConstructorCDAO(){
        assertEquals(eC, cDAO.getEmbeddedConnexion());
    }
    @Test
    void TestConstructorCDTO(){
        assertEquals(uDTO, cDTO.getDemandeur());
        assertEquals(uDTO1, cDTO.getReceveur());
    }
}
