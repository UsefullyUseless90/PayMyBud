package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.Connexion;
import com.paymybuddy.paymybuddy.repositories.ConnexionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/connexions")
public class ConnexionController {
    @Autowired
    ConnexionRepository connexionRepository;

    @GetMapping
    List<Connexion> getConnexions() { return null; }
        /*return connexionRepository.findAll();*/


    @PutMapping("/{demandeur}/connexions/{receveur}")
    void connexionSetUtilisateurs(
            @PathVariable Integer demandeur,
            @PathVariable Integer receveur
    )
    {
        /*Connexion connexion = connexionRepository.getOne(demandeur);
        Connexion connexion2 = connexionRepository.getOne(receveur);*/
    }
}