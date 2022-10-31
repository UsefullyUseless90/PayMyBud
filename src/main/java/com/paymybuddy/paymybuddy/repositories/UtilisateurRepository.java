package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UtilisateurRepository extends CrudRepository<UtilisateurDAO, Integer> {

    UtilisateurDTO findByAdresseEmail(String userName);
}
