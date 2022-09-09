package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurService {


    Optional<Utilisateur> getUtilisateurId();

    Iterable<Utilisateur> getTousLesUtilisateurs();

    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    /*
    List<Utilisateur> utilisateurMaj(Utilisateur utilisateur);
     */
}
