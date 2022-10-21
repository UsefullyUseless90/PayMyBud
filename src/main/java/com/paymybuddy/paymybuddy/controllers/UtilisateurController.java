package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.services.IUtilisateurService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {

    private static final Logger log = LogManager.getLogger(UtilisateurController.class);

    @Autowired
    IUtilisateurService iUtilisateurService;

    @RequestMapping("/user/utilisateurs")
    public ResponseEntity<Iterable<UtilisateurDAO>>getUtilisateurs(){
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des utilisateurs:" + iUtilisateurService.getTousLesUtilisateurs());
        Iterable<UtilisateurDAO> utilisateurs = iUtilisateurService.getTousLesUtilisateurs();
        ResponseEntity<Iterable<UtilisateurDAO>> listeUtilisateurs = ResponseEntity.status(HttpStatus.OK).body(utilisateurs);
        return listeUtilisateurs;
    }
    @RequestMapping(value = "/utilisateur", params = "id", method = RequestMethod.GET)
    public ResponseEntity<Optional<UtilisateurDAO>>getUtilisateurParIdentifiant(@RequestParam Integer idUtilisateur){
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici le resultat de votre demande:" + iUtilisateurService.getUtilisateurId());
        Optional<UtilisateurDAO> id = iUtilisateurService.getUtilisateurId();
        ResponseEntity<Optional<UtilisateurDAO>>identifiantUtilisateur = ResponseEntity.status(HttpStatus.OK).body(id);
        return identifiantUtilisateur;
    }
    @PostMapping
    @RequestMapping("/inscription")
    public ResponseEntity<UtilisateurDAO> saveUtilisateur(@RequestBody UtilisateurDAO utilisateurDAO) throws IOException, JSONException {
        log.info("Création du nouvel utilisateur, veuillez patienter...");
        iUtilisateurService.saveUtilisateur(utilisateurDAO);
        ResponseEntity<UtilisateurDAO> creation = ResponseEntity.status(HttpStatus.CREATED).body(utilisateurDAO);
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
