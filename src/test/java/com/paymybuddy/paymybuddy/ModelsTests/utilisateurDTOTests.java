package com.paymybuddy.paymybuddy.ModelsTests;

import com.paymybuddy.paymybuddy.models.RoleUtilisateurs;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class utilisateurDTOTests {

    UtilisateurDTO uDTO = new UtilisateurDTO();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        uDTO.setIdUtilisateur(789);
        uDTO.setAdresseEmail("SirPath.com");
        uDTO.setNomPrenom("Sir Path");
        uDTO.setMotDePasse("StLaurent");
        uDTO.setActif(true);
        uDTO.roles = new ArrayList<>();
        uDTO.roles.add(RoleUtilisateurs.USER);
        uDTO.setRoles(uDTO.roles);
    }

    @Test
    void testConstructor(){
        assertEquals(789, uDTO.getIdUtilisateur());
        assertEquals("SirPath.com", uDTO.getAdresseEmail());
        assertEquals("Sir Path", uDTO.getNomPrenom());
        assertEquals("StLaurent", uDTO.getMotDePasse());
        assertEquals(true, uDTO.isActif());
        List<RoleUtilisateurs> rolesList = new ArrayList<>();
        rolesList.add(RoleUtilisateurs.USER);
        assertEquals(rolesList, uDTO.getRoles());
    }



}
