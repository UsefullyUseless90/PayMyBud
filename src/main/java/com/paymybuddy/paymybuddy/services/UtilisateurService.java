package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    UtilisateurDAO utilisateurDAO;

    public Iterable<UtilisateurDAO> getTousLesUtilisateurs() {
    Iterable<UtilisateurDAO> utilisateurs = utilisateurRepository.findAll();
    return utilisateurs;
    }

    public Optional<UtilisateurDAO> getUtilisateurId() {
        Optional<UtilisateurDAO> id = utilisateurRepository.findById(utilisateurDAO.getIdUtilisateur());
        return id;
    }
    @Transactional
    public UtilisateurDAO saveUtilisateur(UtilisateurDAO utilisateurDAO){
        utilisateurDAO.setIdUtilisateur(new Random().nextInt());
        utilisateurDAO.setNom(utilisateurDAO.getNom());
        utilisateurDAO.setPrenom(utilisateurDAO.getPrenom());
        utilisateurDAO.setAdresseEmail(utilisateurDAO.getAdresseEmail());
        utilisateurDAO.setDateDeNaissance(utilisateurDAO.getDateDeNaissance());
        utilisateurDAO.setMotDePasse(utilisateurDAO.getMotDePasse());
        utilisateurDAO = utilisateurRepository.save(utilisateurDAO);
        return utilisateurDAO;
    }

}
