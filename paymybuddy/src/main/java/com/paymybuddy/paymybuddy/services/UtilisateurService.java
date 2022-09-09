package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Utilisateur;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    Utilisateur utilisateur;

    public Iterable<Utilisateur> getTousLesUtilisateurs() {
    Iterable<Utilisateur> utilisateurs = utilisateurRepository.findAll();
    return utilisateurs;
    }

    public Optional<Utilisateur> getUtilisateurId() {
        Optional<Utilisateur> id = utilisateurRepository.findById(utilisateur.getIdUtilisateur());
        return id;
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur){
        utilisateur.setIdUtilisateur(new Random().nextInt());
        utilisateur.setNom(utilisateur.getNom());
        utilisateur.setPrenom(utilisateur.getPrenom());
        utilisateur.setAdresseEmail(utilisateur.getAdresseEmail());
        utilisateur.setDateDeNaissance(utilisateur.getDateDeNaissance());
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateur;
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
