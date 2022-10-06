package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ConnexionRepository extends CrudRepository<ConnexionDAO, Integer> {

    @Query("select c from ConnexionDAO c where c.embeddedConnexion.receveur = :utilisateur")
    List<ConnexionDAO> getMyConnexions(@Param("utilisateur") UtilisateurDAO utilisateurDAO);

}
