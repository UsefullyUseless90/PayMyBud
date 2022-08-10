package com.paymybuddy.paymybuddy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@AllArgsConstructor
@Getter
@Setter
public class EmbeddedTransaction implements Serializable {

    private Date date;

    @ManyToOne
    @JoinColumn(name = "emetteur")
    private Utilisateur emetteur;

    @ManyToOne
    @JoinColumn(name = "destinataire")
    private Utilisateur destinataire;

    public EmbeddedTransaction() {

    }
}
