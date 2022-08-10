package com.paymybuddy.paymybuddy.models;

import javax.persistence.*;

@Entity
@Table(name = "Connexion")
public class Connexion {

    @EmbeddedId
    private EmbeddedConnexion embeddedConnexion;

    public Connexion() {
        this.embeddedConnexion = new EmbeddedConnexion();
    }
}
