package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.Utilisateur;
import com.paymybuddy.paymybuddy.services.IUtilisateurService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class UtilisateurController {

    private static final Logger log = LogManager.getLogger(UtilisateurController.class);

    @Autowired
    IUtilisateurService iUtilisateurService;

    @RequestMapping("/utilisateurs")
    public ResponseEntity<Iterable<Utilisateur>>getUtilisateurs(){
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des utilisateurs:" + iUtilisateurService.getTousLesUtilisateurs());
        Iterable<Utilisateur> utilisateurs = iUtilisateurService.getTousLesUtilisateurs();
        ResponseEntity<Iterable<Utilisateur>> listeUtilisateurs = ResponseEntity.status(HttpStatus.OK).body(utilisateurs);
        return listeUtilisateurs;
    }
    @RequestMapping(value = "/utilisateur", params = "id", method = RequestMethod.GET)
    public ResponseEntity<Optional<Utilisateur>>getUtilisateurParIdentifiant(@RequestParam Integer idUtilisateur){
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici le resultat de votre demande:" + iUtilisateurService.getUtilisateurId());
        Optional<Utilisateur> id = iUtilisateurService.getUtilisateurId();
        ResponseEntity<Optional<Utilisateur>>identifiantUtilisateur = ResponseEntity.status(HttpStatus.OK).body(id);
        return identifiantUtilisateur;
    }
    @PostMapping
    @RequestMapping("/inscription")
    public ResponseEntity<Utilisateur> saveUtilisateur(@RequestBody Utilisateur utilisateur) throws IOException, JSONException {
        log.info("Création du nouvel utilisateur, veuillez patienter...");
        iUtilisateurService.saveUtilisateur(utilisateur);
        ResponseEntity<Utilisateur> creation = ResponseEntity.status(HttpStatus.CREATED).body(utilisateur);
        log.info("Un nouvel utilisateur a été crée" + creation);
        return creation;
    }
    /*@PutMapping
    public ResponseEntity<?> updatePerson(@RequestBody Utilisateur utilisateur) throws IOException, JSONException {
        log.info("Checking, please wait...");
        List<Utilisateur> updatedUtilisateur = iUtilisateurService.utilisateurupdate(utilisateur);
        log.info("Person updated!" + updatedPerson);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

     */
}
