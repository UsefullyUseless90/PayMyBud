package com.paymybuddy.paymybuddy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="compte")
@Component
@Entity
@Setter
@Getter
@NoArgsConstructor
public class CompteBancaire {

    @Id
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;
    @Column(name="id_compte")
    private String idCompte;
    @Column(name="iban")
    private String iban;
    @Column(name="bic")
    private String bic;
    @Column(name="banque")
    private String banque;
    @Column(name="fonds")
    private double fonds;

    public CompteBancaire( Integer idUtilisateur, String idCompte,String iban, String bic, String banque, double fonds) {
        this.idUtilisateur = idUtilisateur;
        this.idCompte = idCompte;
        this.iban = iban;
        this.bic = bic;
        this.banque = banque;
        this.fonds = fonds;
    }
}
