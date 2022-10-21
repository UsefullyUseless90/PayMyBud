package com.paymybuddy.paymybuddy.models.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Embeddable
@Component
@Getter
@Setter
public class EmbeddedTransaction implements Serializable {

    public EmbeddedTransaction() {
    }
    public EmbeddedTransaction(Date date, UtilisateurDAO emetteur, UtilisateurDAO destinataire) {
        this.date = date;
        this.emetteur = emetteur;
        this.destinataire = destinataire;
    }

    @Column
    private Date date;


    @ManyToOne
    @JoinColumn(name = "emetteur")
    private UtilisateurDAO emetteur;


    @ManyToOne
    @JoinColumn(name = "destinataire")
    private UtilisateurDAO destinataire;


}
