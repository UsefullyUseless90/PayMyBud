package com.paymybuddy.paymybuddy.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "connexions")
@Setter
@Getter
public class Connexion {

    @EmbeddedId
    private EmbeddedConnexion embeddedConnexion;

    public Connexion() {
        this.embeddedConnexion = new EmbeddedConnexion();
    }
}
