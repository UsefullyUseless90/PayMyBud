package com.paymybuddy.paymybuddy.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
public class Utilisateur {
    @Id
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    private String nom;
    private String prenom;
    private String adresseEmail;
    private Date dateDeNaissance;
    private double fondsDisponibles;

    @OneToMany(mappedBy = "embeddedTransaction.emetteur")
    private List<Transaction> transactions;


    @OneToMany(mappedBy = "embeddedConnexion.demandeur")
    Set<Connexion>ConnexionSet = new HashSet<>();

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public Utilisateur(int idUtilisateur, String nom, String prenom, String adresseEmail, Date dateDeNaissance, double fondsDisponibles) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseEmail = adresseEmail;
        this.dateDeNaissance = dateDeNaissance;
        this.fondsDisponibles = fondsDisponibles;

    }
}
