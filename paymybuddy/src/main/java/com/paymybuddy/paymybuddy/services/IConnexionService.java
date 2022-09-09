package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Connexion;
import org.springframework.stereotype.Component;

import java.util.Optional;


public interface IConnexionService {

    Iterable<Connexion> getConnexions();

    Optional<Connexion> getConnexionById();
}
