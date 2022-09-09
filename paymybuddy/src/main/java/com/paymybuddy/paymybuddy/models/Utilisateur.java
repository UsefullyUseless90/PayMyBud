package com.paymybuddy.paymybuddy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Component
@Table(name = "utilisateurs")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utilisateur {
    @Id
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "adresse_email")
    private String adresseEmail;
    @Column(name = "date_de_naissance")
    private Date dateDeNaissance;
    @Column(name = "fonds_disponibles")
    private double fondsDisponibles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "embeddedTransaction.emetteur")
    private List<Transaction> transactions;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "embeddedConnexion.demandeur")
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
