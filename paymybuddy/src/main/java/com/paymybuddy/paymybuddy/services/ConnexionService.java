package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.Connexion;
import com.paymybuddy.paymybuddy.repositories.ConnexionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class ConnexionService implements IConnexionService {

    @Autowired
    private ConnexionRepository connexionRepository;
    @Autowired
    Connexion connexion;

    public Iterable<Connexion> getConnexions(){
        return connexionRepository.findAll();
    }
    public Optional<Connexion> getConnexionById(){
        return connexionRepository.findById(connexion.getEmbeddedConnexion().getDemandeur().getIdUtilisateur());
    }
}
