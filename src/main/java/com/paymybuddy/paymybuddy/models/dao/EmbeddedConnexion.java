package com.paymybuddy.paymybuddy.models.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Component
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class EmbeddedConnexion implements Serializable {

    @ManyToOne
    @JoinColumn
    private UtilisateurDAO receveur;

    @ManyToOne
    @JoinColumn
    private UtilisateurDAO demandeur;

    public EmbeddedConnexion(UtilisateurDAO receveur, UtilisateurDAO demandeur) {
        this.receveur = receveur;
        this.demandeur = demandeur;
    }
}
