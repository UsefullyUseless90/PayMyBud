package com.paymybuddy.paymybuddy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class EmbeddedConnexion implements Serializable {

    @ManyToOne
    @JoinColumn
    private Utilisateur receveur;

    @ManyToOne
    @JoinColumn
    private Utilisateur demandeur;
}
