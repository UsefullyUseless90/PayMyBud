package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.CompteBancaire;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<CompteBancaire, Long> {

    CompteBancaire findByIdCompte(String idCompte);

    CompteBancaire findByIdUtilisateur(int idUtilisateur);
}
