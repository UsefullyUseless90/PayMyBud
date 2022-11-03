package com.paymybuddy.paymybuddy.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "commissions_pmb")
@Getter
@Setter
@NoArgsConstructor
public class Commissions_pmb {


    private double montant;
    @Id
    @Column(name = "numero_transaction", nullable = false)
    private int numeroTransaction;

}
