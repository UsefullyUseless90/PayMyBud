package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.Connexion;
import com.paymybuddy.paymybuddy.services.IConnexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/connexions")
public class ConnexionController {

    @Autowired
    IConnexionService iConnexionService;

    @GetMapping
    @RequestMapping("/connexions")
    public Iterable<Connexion>getConnexions() {
        Iterable<Connexion> connexions = iConnexionService.getConnexions();
        return connexions;
    }

    @RequestMapping("/connexions/id")
    public Optional<Connexion>getRelation() {
        Optional<Connexion> relation = iConnexionService.getConnexionById();
        return relation;
    }





}