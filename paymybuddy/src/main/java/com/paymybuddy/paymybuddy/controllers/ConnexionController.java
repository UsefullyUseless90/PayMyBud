package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.services.IConnexionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/connexions")
public class ConnexionController {

    private static final Logger log = LogManager.getLogger(TransactionController.class);

    @Autowired
    IConnexionService iConnexionService;

    @RequestMapping("/{id}")
    public List<UtilisateurDTO> getRelation(@PathVariable("id") Integer idUtilisateur) {
        return iConnexionService.getConnexionById(idUtilisateur);
    }
    @PostMapping("/nouvelleConnexion")
    public ResponseEntity<?> creationConnexion(@RequestBody ConnexionDTO connexionDTO) throws IOException, JSONException {
        log.info("Création d'une nouvelle connexion, veuillez patienter...");
        iConnexionService.creationConnexion(connexionDTO);
        ResponseEntity<?> creation = ResponseEntity.status(HttpStatus.CREATED).body(connexionDTO);
        log.info("Une nouvelle connexion a été crée" + creation);

        return creation;
    }




}