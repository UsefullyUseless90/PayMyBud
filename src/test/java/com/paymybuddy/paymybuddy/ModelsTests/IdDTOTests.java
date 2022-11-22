package com.paymybuddy.paymybuddy.ModelsTests;

import com.paymybuddy.paymybuddy.models.dto.IdentificationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdDTOTests {


    String id = "235";
    String token = "ultrasecrettokenquonpeutpasdechiffrer";
    IdentificationDTO identificationDTO = new IdentificationDTO(id, token);

    @BeforeEach
    void setUp(){
        identificationDTO.setToken(token);
        identificationDTO.setIdUtilisateur(id);
    }

  @Test
    void TestConstructorId(){
      assertEquals(token, identificationDTO.getToken());
      assertEquals(id, identificationDTO.getIdUtilisateur());
  }

}
