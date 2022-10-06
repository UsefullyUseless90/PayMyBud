package com.paymybuddy.paymybuddy.services;

import com.paymybuddy.paymybuddy.models.dao.ConnexionDAO;
import com.paymybuddy.paymybuddy.models.dao.TransactionDAO;
import com.paymybuddy.paymybuddy.models.dao.UtilisateurDAO;
import com.paymybuddy.paymybuddy.models.dto.ConnexionDTO;
import com.paymybuddy.paymybuddy.models.dto.TransactionDTO;
import com.paymybuddy.paymybuddy.models.dto.UtilisateurDTO;
import com.paymybuddy.paymybuddy.repositories.ConnexionRepository;
import com.paymybuddy.paymybuddy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
class ConnexionService implements IConnexionService {

    @Autowired
    private ConnexionRepository connexionRepository;


    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Iterable<ConnexionDAO> getConnexions(){
        return connexionRepository.findAll();
    }

    public List<UtilisateurDTO> getConnexionById(Integer idUtilisateur){
        UtilisateurDAO utilisateurDAO = utilisateurRepository.findById(idUtilisateur).orElse(null);

        List<UtilisateurDTO> listeConnexion = new ArrayList<>();

        Iterator<ConnexionDAO> connexion = utilisateurDAO.getConnexionDAOSet().iterator();

        while(connexion.hasNext()) {
            ConnexionDAO connexionDAO = connexion.next();

            UtilisateurDTO nouvelUtilisateurDTO = new UtilisateurDTO(connexionDAO.getEmbeddedConnexion().getDemandeur());

            listeConnexion.add(nouvelUtilisateurDTO);

        }


        return listeConnexion;
    }

    @Override
    public ConnexionDTO creationConnexion(ConnexionDTO connexionDTO) {
        UtilisateurDAO demandeurDAO =  utilisateurRepository.findById(connexionDTO.getDemandeur().getIdUtilisateur()).orElse(null);
        UtilisateurDAO receveurDAO =  utilisateurRepository.findById(connexionDTO.getReceveur().getIdUtilisateur()).orElse(null);

        ConnexionDAO connexionDAO = new ConnexionDAO(demandeurDAO,receveurDAO);

        connexionDAO = connexionRepository.save(connexionDAO);

        return new ConnexionDTO(connexionDAO);
    }

}
