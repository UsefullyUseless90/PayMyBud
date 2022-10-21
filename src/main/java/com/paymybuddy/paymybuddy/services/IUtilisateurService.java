package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;

import java.util.Optional;

public interface IUtilisateurService {


    Optional<UtilisateurDAO> getUtilisateurId();

    Iterable<UtilisateurDAO> getTousLesUtilisateurs();

    UtilisateurDAO saveUtilisateur(UtilisateurDAO utilisateurDAO);

    /*
    List<Utilisateur> utilisateurMaj(Utilisateur utilisateur);
     */
}
