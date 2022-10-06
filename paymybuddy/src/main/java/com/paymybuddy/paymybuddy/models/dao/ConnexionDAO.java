package com.paymybuddy.paymybuddy.models.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class ConnexionDAO {

    @EmbeddedId
    private EmbeddedConnexion embeddedConnexion;

    public ConnexionDAO(UtilisateurDAO receveur, UtilisateurDAO demandeur){
        this.embeddedConnexion = new EmbeddedConnexion(demandeur, receveur);
    }
}
