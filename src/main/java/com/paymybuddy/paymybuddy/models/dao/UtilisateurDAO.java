package com.paymybuddy.paymybuddy.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paymybuddy.paymybuddy.models.CompteBancaire;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Component
@Table(name = "utilisateurs")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UtilisateurDAO {
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
    @NotNull
    @Column(name = "mot_de_passe")
    private String motDePasse;

    @OneToMany
    @JsonIgnore
    private List<CompteBancaire> banques = new ArrayList<>();

    @OneToMany( mappedBy = "embeddedTransaction.emetteur", cascade=CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<TransactionDAO> transactionDAOS;


   @OneToMany(mappedBy = "embeddedConnexion.receveur", cascade=CascadeType.ALL)
   @JsonIgnore
   Set<ConnexionDAO> connexionDAOSet = new HashSet<>();

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public UtilisateurDAO(int idUtilisateur, String nom, String prenom, String motDePasse, String adresseEmail, Date dateDeNaissance, double fondsDisponibles) {

        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseEmail = adresseEmail;
        this.dateDeNaissance = dateDeNaissance;
        this.fondsDisponibles = fondsDisponibles;
        this.motDePasse = motDePasse;

    }
}
