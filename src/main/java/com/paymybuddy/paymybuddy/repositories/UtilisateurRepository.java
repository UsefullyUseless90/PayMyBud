package com.paymybuddy.paymybuddy.repositories;

import com.paymybuddy.paymybuddy.models.Utilisateur;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UtilisateurRepository {

    List<Utilisateur> findAllUtilisateur();
    Utilisateur findByIdUtilisateur(@Param("idUtilisateur") Integer idUtilisateur);
    List<Utilisateur> findByPrenom(@Param("prenom") String prenom);
    List<Utilisateur> findByNom(@Param("nom") String nom);
    Utilisateur findByAdresseEmail(@Param("adresseEmail") String adresseEmail);
    List<Utilisateur> findByFondsDisponibles(@Param("fondsDisponibles") double fondsDisponibles);
    List<Utilisateur> findByDateDeNaissance(@Param("dateDeNaissance")Date dateDeNaissance);

}
