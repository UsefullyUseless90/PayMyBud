package com.paymybuddy.paymybuddy.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user/accueil")
public class AuthController{

    private static final Logger log = LogManager.getLogger(AuthController.class);

    @GetMapping
    public String messageDeBienvenue(){
        log.info("Login success!");
        return "Bienvenue chez PayMyBuddy";
    }
}