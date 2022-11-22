package com.paymybuddy.paymybuddy.controllers;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UtilisateurController {

    private static final Logger log = LogManager.getLogger(UtilisateurController.class);

    @Autowired
    IUtilisateurService iUtilisateurService;
    @Autowired
    UtilisateurRepository repo;

    @RequestMapping("/user/utilisateurs")
    public ResponseEntity<Iterable<UtilisateurDAO>>getUtilisateurs(){
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des utilisateurs:" + iUtilisateurService.getTousLesUtilisateurs());
        Iterable<UtilisateurDAO> utilisateurs = iUtilisateurService.getTousLesUtilisateurs();
        ResponseEntity<Iterable<UtilisateurDAO>> listeUtilisateurs = ResponseEntity.status(HttpStatus.OK).body(utilisateurs);
        return listeUtilisateurs;
    }
    @RequestMapping(value = "/utilisateur/{id}")
    public ResponseEntity<Optional<UtilisateurDAO>>getUtilisateurParIdentifiant(@PathVariable("id") Integer idUtilisateur){
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici le resultat de votre demande:" + repo.findById(idUtilisateur));
        Optional<UtilisateurDAO> id = repo.findById(idUtilisateur);
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
    @RequestMapping("/user/accueil")
    public String messageDeBienvenue(){
        log.info("Login success!");
        return "Bienvenue chez PayMyBuddy";
    }
    @PutMapping
    @RequestMapping("/user/edit/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody UtilisateurDAO utilisateur, @PathVariable("id") Integer idUtilisateur) throws IOException, JSONException {
        log.info("Checking, please wait...");
        Optional<UtilisateurDAO> updatedUtilisateur = repo.findById(idUtilisateur);
        if(updatedUtilisateur.isPresent()){
            UtilisateurDAO update = updatedUtilisateur.get();
            update.setNom(utilisateur.getNom());
            update.setPrenom(utilisateur.getPrenom());
            update.setAdresseEmail(utilisateur.getAdresseEmail());
            repo.save(update);
        }
        log.info("Person updated!" + updatedUtilisateur);
        return new ResponseEntity<>(updatedUtilisateur, HttpStatus.OK);
    }

}
