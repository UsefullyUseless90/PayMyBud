package com.paymybuddy.paymybuddy.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Utilisateur {
    @Id
    @Column(nullable = false)
    @JoinColumn(name ="id_utilisateur")
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String adresseEmail;
    private Date dateDeNaissance;
    private double fondsDisponibles;

    @ManyToOne
    @JoinTable(
        name = "transactions",
        joinColumns = { @JoinColumn(name="destinataire"),
                        @JoinColumn(name="emetteur"),
                        @JoinColumn(name = "date")}
    )

    private Transaction transaction;

    @ManyToMany
    @JoinTable(
        name = "connexions",
        joinColumns = @JoinColumn(name = "demandeur"),
            inverseJoinColumns = @JoinColumn(name="receveur"))
    Set<Connexion>ConnexionSet;

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
