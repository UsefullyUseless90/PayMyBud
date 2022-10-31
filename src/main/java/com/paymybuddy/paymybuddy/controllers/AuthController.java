package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.config.JwtManager;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class AuthController{

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtManager jwt;

    private static final Logger log = LogManager.getLogger(AuthController.class);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object[] login(@RequestBody UtilisateurDAO user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getAdresseEmail(), user.getMotDePasse()));

        UtilisateurDTO userDetails = (UtilisateurDTO) authentication.getCredentials();

        return new Object[] {userDetails.getIdUtilisateur(), jwt.generateToken(userDetails)};
    }




}