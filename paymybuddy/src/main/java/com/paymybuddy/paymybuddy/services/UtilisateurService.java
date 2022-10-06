package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UtilisateurDAO saveUtilisateur(UtilisateurDAO utilisateurDAO){
        utilisateurDAO.setIdUtilisateur(new Random().nextInt());
        utilisateurDAO.setNom(utilisateurDAO.getNom());
        utilisateurDAO.setPrenom(utilisateurDAO.getPrenom());
        utilisateurDAO.setAdresseEmail(utilisateurDAO.getAdresseEmail());
        utilisateurDAO.setDateDeNaissance(utilisateurDAO.getDateDeNaissance());
        utilisateurDAO = utilisateurRepository.save(utilisateurDAO);
        return utilisateurDAO;
    }
   /* List<Utilisateur> utilisateurMaj(Utilisateur utilisateur){
        // Creates a list and add to it the persons
        Iterable<Utilisateur> personList = this.getTousLesUtilisateurs();

        for (int i = 0; i < personList.size(); i++) {
            Utilisateur utilisateur = personList.get(i);
            // In case of any match the value is replaced by a new one
            if (p.getLastName().equals(person.getLastName()) && p.getFirstName().equals(person.getFirstName())) {
                personList.set(i, person);
                break;
            }
        }
        return utilisateurList;
    }

    */
}
