package com.paymybuddy.paymybuddy.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Connexion")
public class Connexion {

    @Id
    private int receveur;
    @Id
    private int demandeur;

    @ManyToMany(mappedBy = "ConnexionSet")
    Set<Utilisateur>MiseEnRelation;


    public Connexion() {
        this.receveur = receveur;
        this.demandeur = demandeur;
    }
}
