package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionDAO, Integer> {

    @Query("select c from Transaction c where c.embeddedTransaction.emetteur = :utilisateur")
    List<TransactionDAO> getMyTransactions(@Param("utilisateur") UtilisateurDAO utilisateurDAO);


}
