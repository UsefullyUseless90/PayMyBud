package com.paymybuddy.paymybuddy.controllers;


import com.paymybuddy.paymybuddy.models.CompteBancaire;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.ConnexionBanqueDTO;
import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.TransfertEntrantDTO;
import com.paymybuddy.paymybuddy.repositories.CompteRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import com.paymybuddy.paymybuddy.services.ICompteBancaireService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/transfert/")
public class CompteBancaireController {

    private static final Logger log = LogManager.getLogger(CompteBancaireController.class);

    @Autowired
    private ICompteBancaireService iCompteBancaireService;
    @Autowired
    private UtilisateurRepository uRepo;
    @Autowired
    private CompteRepository cRepo;

    @RequestMapping("/index/{id}")
    public ResponseEntity<Iterable<CompteBancaire>>getTousComptesB(@PathVariable("id") Integer idUtilisateur){
        log.info("Recherche en cours, veuillez patienter...");
        log.info("Voici une liste de l'ensemble des utilisateurs:" + iCompteBancaireService.getComptes(idUtilisateur));
        Iterable<CompteBancaire> comptes = iCompteBancaireService.getComptes(idUtilisateur);
        ResponseEntity<Iterable<CompteBancaire>> listeComptes = ResponseEntity.status(HttpStatus.OK).body(comptes);
        return listeComptes;
    }

    @PostMapping("ajout/{id}")
    public ResponseEntity<TransfertEntrantDTO> ajoutMonnaie(@RequestBody TransfertEntrantDTO transfertEntrant,@PathVariable ("id") int idUtilisateur) throws Exception {
        log.info("Création d'une nouvelle transaction, veuillez patienter...");
        iCompteBancaireService.ajoutFondsComptePmb(transfertEntrant, idUtilisateur);
        ResponseEntity<TransfertEntrantDTO> creation = ResponseEntity.status(HttpStatus.CREATED).body(transfertEntrant);
        log.info("Une nouvelle transaction a été crée" + creation);

        return creation;
    }
    @PostMapping
    @RequestMapping ("retrait/{id}")
    public ResponseEntity<TransfertEntrantDTO> retraitMonnaie(@RequestBody TransfertEntrantDTO transfertEntrant, @PathVariable ("id") int idUtilisateur) throws Exception {
        log.info("Création d'une nouvelle transaction, veuillez patienter...");
        iCompteBancaireService.retraitFondsComptePmb(transfertEntrant, idUtilisateur);
        ResponseEntity<TransfertEntrantDTO> creation = ResponseEntity.status(HttpStatus.CREATED).body(transfertEntrant);
        log.info("Une nouvelle transaction a été crée" + creation);

        return creation;
    }
    @PostMapping
    @RequestMapping("nouvelleConnexion/{id}")
    public ResponseEntity<?> creationConnexion(@RequestBody CompteBancaire compteBancaire, @PathVariable ("id") int idUtilisateur){
        log.info("Création d'une nouvelle connexion, veuillez patienter...");
        compteBancaire.setIdUtilisateur(idUtilisateur);
        iCompteBancaireService.saveCompteBancaire(compteBancaire);
        Optional<UtilisateurDAO> userBank = uRepo.findById(idUtilisateur);
        if(userBank.isPresent()){
            UtilisateurDAO update = userBank.get();
            List<CompteBancaire> banks = new ArrayList<>();
            banks.add(compteBancaire);
            update.setBanques(banks);
        }
        ResponseEntity<?> creation = ResponseEntity.status(HttpStatus.CREATED).body(compteBancaire);
        log.info("Une nouvelle connexion a été crée" + creation);

        return creation;
    }

}
