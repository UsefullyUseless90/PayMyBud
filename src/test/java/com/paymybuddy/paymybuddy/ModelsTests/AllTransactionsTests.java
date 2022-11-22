package com.paymybuddy.paymybuddy.ModelsTests;

import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dao.EmbeddedTransaction;
import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllTransactionsTests {

    TransactionDTO tDTO = new TransactionDTO();
    TransactionDAO tDAO = new TransactionDAO();
    EmbeddedTransaction eT = new EmbeddedTransaction();
    UtilisateurDTO uDTO = new UtilisateurDTO();
    UtilisateurDTO uDTO1 = new UtilisateurDTO();
    UtilisateurDAO uDAO = new UtilisateurDAO();
    UtilisateurDAO uDAO1 = new UtilisateurDAO();
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

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

        //TransactionDAO//

        tDAO.setDescription("aaaaaaaaaa");
        tDAO.setMontant(25);
        tDAO.setEmbeddedTransaction(eT);

        //TransactionDTO//
        tDTO.setDestinataire(uDTO1);
        tDTO.setEmetteur(uDTO);
        tDTO.setDescription(tDAO.getDescription());
        tDTO.setDate(tDAO.getEmbeddedTransaction().getDate());
        tDTO.setMontant(tDAO.getMontant());


        //EmbeddedTransaction//

        eT.setEmetteur(uDAO);
        eT.setDate(new Date(2022, 11, 18 ));
        eT.setDestinataire(uDAO1);

    }

    @Test
    void testConstructorUtilisateurDAO(){

        // 0 //
        assertEquals("Jacques", uDAO.getPrenom());
        assertEquals("Cartier", uDAO.getNom());
        assertEquals(1990, uDAO.getFondsDisponibles());
        assertEquals("QCRules.com",uDAO.getAdresseEmail());
        assertEquals(new Date(1431, 9, 01) , uDAO.getDateDeNaissance());
        assertEquals(1431, uDAO.getIdUtilisateur());
        assertEquals("Quebec", uDAO.getMotDePasse());

        // 1 //
        assertEquals("Rene",uDAO1.getPrenom());
        assertEquals("Leveque", uDAO1.getNom());
        assertEquals(1990, uDAO1.getFondsDisponibles() );
        assertEquals("QCRules1.com", uDAO1.getAdresseEmail());
        assertEquals(new Date(1922, 11, 25),uDAO1.getDateDeNaissance());
        assertEquals(1922, uDAO1.getIdUtilisateur());
        assertEquals("PartiQuebec", uDAO1.getMotDePasse());

    }
    @Test
    void testConstructorUtilisateurDTO(){

        // 0 //
        assertEquals(789 ,uDTO.getIdUtilisateur());
        assertEquals("SirPath.com",uDTO.getAdresseEmail());
        assertEquals("Sir Path",uDTO.getNomPrenom());
        assertEquals("StLaurent",uDTO.getMotDePasse());
        assertEquals(true ,uDTO.isActif());
        List<RoleUtilisateurs> rolesList = new ArrayList<>();
        rolesList.add(RoleUtilisateurs.USER);
        assertEquals(rolesList, uDTO.getRoles());

        // 1 //
        assertEquals(879, uDTO1.getIdUtilisateur());
        assertEquals("PathSir.com", uDTO1.getAdresseEmail());
        assertEquals("PathSir", uDTO1.getNomPrenom());
        assertEquals("StLaurent", uDTO1.getMotDePasse());
        assertEquals(true, uDTO1.isActif());
        assertEquals(rolesList, uDTO1.getRoles());
    }
    @Test
    void TestConstructorTransactionDAO(){
       assertEquals("aaaaaaaaaa", tDAO.getDescription());
       assertEquals(25 ,tDAO.getMontant());
       assertEquals(eT, tDAO.getEmbeddedTransaction());
    }
    @Test
    void TestConstructorTransactionDTO(){
        assertEquals (uDTO1, tDTO.getDestinataire());
        assertEquals (uDTO, tDTO.getEmetteur());
        assertEquals(tDAO.getDescription(), tDTO.getDescription());
        assertEquals(null,tDTO.getDate());
        assertEquals(tDAO.getMontant(),tDTO.getMontant());
    }
    @Test
    void TestConstructorEmbeddedTransactionDTO(){
        assertEquals(uDAO, eT.getEmetteur());
        assertEquals(new Date(2022, 11, 18 ), eT.getDate());
        assertEquals(uDAO1, eT.getDestinataire());
    }
}
