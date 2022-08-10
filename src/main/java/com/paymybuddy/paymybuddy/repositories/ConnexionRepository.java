package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.Connexion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories("com.paymybuddy.paymybuddy.repositories")
public interface ConnexionRepository extends JpaRepository<Connexion, Integer> {



}
