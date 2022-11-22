package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;

import java.util.List;


public interface IConnexionService {

    Iterable<ConnexionDAO> getConnexions();

    List<UtilisateurDTO> getConnexionById(Integer idUtilisateur);

    ConnexionDTO creationConnexion(ConnexionDTO connexionDTO, Integer idUtilisateur);
}
