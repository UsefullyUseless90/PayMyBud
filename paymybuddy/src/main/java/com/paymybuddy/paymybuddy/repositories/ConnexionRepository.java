package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.Connexion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnexionRepository extends CrudRepository<Connexion, Integer> {



}
